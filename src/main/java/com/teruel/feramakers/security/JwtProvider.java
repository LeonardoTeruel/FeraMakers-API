package com.teruel.feramakers.security;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.teruel.feramakers.exceptions.FeraMakersException;
import org.springframework.security.core.userdetails.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.util.Date;

import static io.jsonwebtoken.Jwts.parser;
import static java.util.Date.from;


@Service
public class JwtProvider {

        private KeyStore keyStore;
        //@Value("${jwt.expiration.time}")
        //private Long jwtExpirationInMillis;

        @PostConstruct
        public void init() {
            try {
                keyStore = KeyStore.getInstance("JKS");
                InputStream resourceAsStream = getClass().getResourceAsStream("/feramakers.jks");
                keyStore.load(resourceAsStream, "criptografia1870teruel".toCharArray());
            } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
                throw new FeraMakersException("Exception occurred while loading keystore", e);
            }

        }

        public String generateToken(Authentication authentication) {
            User principal = (User) authentication.getPrincipal();
            System.out.println(getPrivateKey());
            return Jwts.builder()
                    .setSubject(principal.getUsername())
                    .setIssuedAt(from(Instant.now()))
                    .signWith(getPrivateKey())
                    //.setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                    .compact();
        }

        public String generateTokenWithUserName(String username) {
            return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(from(Instant.now()))
                    .signWith(getPrivateKey())
                    //.setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                    .compact();
        }

        private PrivateKey getPrivateKey() {
            try {
                return (PrivateKey) keyStore.getKey("feramakers", "criptografia1870teruel".toCharArray());
            } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
                throw new FeraMakersException("Exception occured while retrieving public key from keystore", e);
            }
        }

        public boolean validateToken(String jwt) {
            parser().setSigningKey(getPublickey()).parseClaimsJws(jwt);
            return true;
        }

        private PublicKey getPublickey() {
            try {
                return keyStore.getCertificate("feramakers").getPublicKey();
            } catch (KeyStoreException e) {
                throw new FeraMakersException("Exception occured while " +
                        "retrieving public key from keystore", e);
            }
        }

        public String getUsernameFromJwt(String token) {
            Claims claims = parser()
                    .setSigningKey(getPublickey())
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();
        }

//        public Long getJwtExpirationInMillis() {
//            return jwtExpirationInMillis;
//        }


}
