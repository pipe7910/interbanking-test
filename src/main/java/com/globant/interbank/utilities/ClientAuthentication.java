package com.globant.interbank.utilities;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ClientAuthentication {

    private final String secret = "123@abc";
    private final Algorithm algorithm = Algorithm.HMAC512(secret);
    private final long expireTime = (new Date().getTime()) + 900000; // 60000 milliseconds = 60 seconds = 1 minute
    private final Date expireDate = new Date(expireTime);
    private final String issuer = "ariasDev";

    public String generateToken() {
        return JWT.create().withIssuer(issuer).withExpiresAt(expireDate).sign(algorithm);
    }

    public boolean isValidToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .acceptExpiresAt(60)
                    .build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException ex) {
            System.out.println("Verify JWT token fail: " + ex.getMessage());
        }
        return false;
    }
}
