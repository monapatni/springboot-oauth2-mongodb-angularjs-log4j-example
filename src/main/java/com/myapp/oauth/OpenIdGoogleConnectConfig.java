package com.myapp.oauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Configuration
@EnableOAuth2Client
public class OpenIdGoogleConnectConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenIdGoogleConnectConfig.class);
    @Value("${google.clientId}")
    private String clientId;

    @Value("${google.clientSecret}")
    private String clientSecret;

    @Value("${google.accessTokenUri}")
    private String accessTokenUri;

    @Value("${google.userAuthorizationUri}")
    private String userAuthorizationUri;

    @Value("${google.redirectUri}")
    private String redirectUri;

    @Bean
    public OAuth2ProtectedResourceDetails googleOpenId() {
        LOGGER.info("inside GoogleOpenIdConnectConfig -> googleOpenId ");
        AuthorizationCodeResourceDetails authorizationCodeResourceDetails = new AuthorizationCodeResourceDetails();
        authorizationCodeResourceDetails.setClientId(clientId);
        authorizationCodeResourceDetails.setClientSecret(clientSecret);
        authorizationCodeResourceDetails.setAccessTokenUri(accessTokenUri);
        authorizationCodeResourceDetails.setUserAuthorizationUri(userAuthorizationUri);
        authorizationCodeResourceDetails.setScope(Arrays.asList("openid", "email"));
        authorizationCodeResourceDetails.setPreEstablishedRedirectUri(redirectUri);
        authorizationCodeResourceDetails.setUseCurrentUri(false);
        LOGGER.info("End GoogleOpenIdConnectConfig -> googleOpenId ");
        return authorizationCodeResourceDetails;
    }

    @Bean
    public OAuth2RestTemplate googleOpenIdTemplate(OAuth2ClientContext clientContext) {
        return new OAuth2RestTemplate(googleOpenId(), clientContext);
    }
}
