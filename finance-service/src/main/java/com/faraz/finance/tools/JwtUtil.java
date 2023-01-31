package com.faraz.finance.tools;

import com.faraz.finance.exception.ExpectedTokenValueException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    @Value("${security.oauth2.client.publicKey}")
    private String publicKey;

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {

            claims = Jwts.parser()
                    .setSigningKey(getKey(publicKey))
                    .parseClaimsJws(token)
                    .getBody();

        } catch (Exception e) {
            e.printStackTrace();
            claims = null;
        }
        return claims;
    }


    public Integer getCompanyIdFromHeader(HttpServletRequest request) {
        String token = getTokenFromHeader(request);
        Integer companyId;
        try {
            final Claims claims = getClaimsFromToken(token);
            companyId = (Integer) claims.get("companyId");

        } catch (Exception e) {
            e.printStackTrace();
            companyId = null;
        }
        if (companyId == null) {
            throw new ExpectedTokenValueException("error.token.value.exception", "companyId");
        }
        return companyId;
    }


    public Integer getPersonIdFromHeader(HttpServletRequest request) {
        String token = getTokenFromHeader(request);
        Integer personId;
        try {
            final Claims claims = getClaimsFromToken(token);
            personId = (Integer) claims.get("personId");

        } catch (Exception e) {
            e.printStackTrace();
            personId = null;
        }
        if (personId == null) {
            throw new ExpectedTokenValueException("error.token.value.exception", "personId");
        }
        return personId;
    }

    public static String getTokenFromHeader(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        token = token.replace("Bearer ", "");
        return token;
    }


    public static PublicKey getKey(String key) {
        try {
            String pubKeyPEM = key.replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "");
            byte[] encodedPublicKey = Base64.getMimeDecoder().decode(pubKeyPEM);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(encodedPublicKey);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePublic(spec);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
