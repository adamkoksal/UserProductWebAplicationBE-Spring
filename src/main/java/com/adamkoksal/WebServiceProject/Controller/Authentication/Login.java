package com.adamkoksal.WebServiceProject.Controller.Authentication;

import com.adamkoksal.WebServiceProject.Entity.Token;
import com.adamkoksal.WebServiceProject.Entity.User;
import com.adamkoksal.WebServiceProject.Service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4201")
@RestController
@RequestMapping("/login")
public class Login {
    private String SECRET_KEY = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> loginUser(@RequestBody User user) throws SQLException {
         if (userService.loginUser(user)) {

             String token = buildJwt(user);

             Map<Object, Object> model = new HashMap<>();
             model.put("token", token);
             return new ResponseEntity<Object>(model,HttpStatus.OK);
         } else {
             return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
         }
    }

    private String buildJwt(User user) throws SQLException {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        int id = userService.getId(user);

        JwtBuilder builder = Jwts.builder()
                .setId(Integer.toString(id)).signWith(signatureAlgorithm, signingKey);;

        return builder.compact();
    }

    @RequestMapping(value = "/is-manager", method = RequestMethod.POST)
    public ResponseEntity<Object> isManager(@RequestBody Token token) throws SQLException {


        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
            .parseClaimsJws(token.toString()).getBody();

        boolean isManager = userService.isManager(Integer.parseInt(claims.getId()));

        return new ResponseEntity<Object>(isManager,HttpStatus.OK);

    }



}
