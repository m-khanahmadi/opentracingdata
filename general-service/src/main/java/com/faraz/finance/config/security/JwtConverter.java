package com.faraz.finance.config.security;

import com.faraz.finance.tools.AccessTokenMapper;
import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class JwtConverter extends DefaultAccessTokenConverter implements JwtAccessTokenConverterConfigurer {

    @Override
    public void configure(JwtAccessTokenConverter converter) {
        converter.setAccessTokenConverter(this);
    }

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        OAuth2Authentication auth = super.extractAuthentication(map);
        AccessTokenMapper details = new AccessTokenMapper();

        if (map.get("id") != null)
            details.setId((Integer) map.get("id"));

        if (map.get("userName") != null)
            details.setUserName((String) map.get("userName"));

        if (map.get("name") != null)
            details.setName((String) map.get("name"));

        if (map.get("companyId") != null)
            details.setCompanyId((Integer) map.get("companyId"));

        if (map.get("personId") != null)
            details.setPersonId((Integer) map.get("personId"));

        auth.setDetails(details);
        System.out.println("jwt="+auth);
        return auth;
    }
}
