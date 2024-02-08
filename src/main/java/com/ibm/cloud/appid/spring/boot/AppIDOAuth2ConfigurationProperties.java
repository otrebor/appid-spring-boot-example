/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * © Copyright IBM Corporation 2019.
 * LICENSE: Apache 2.0 https://www.apache.org/licenses/LICENSE-2.0
 */

package com.ibm.cloud.appid.spring.boot;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

/**
 * OAuth 2.0 client properties.
 */

@ConfigurationProperties(prefix="spring.security.oauth2.client")
public class AppIDOAuth2ConfigurationProperties {
    /**
     * OAuth provider details.
     */
    private final Map<String, Provider> provider = new HashMap<>();
    
    /**
     * OAuth client registrations.
     */
    private final Map<String, Registration> registration = new HashMap<>();
    
    public Map<String, Provider> getProvider() {
        return this.provider;
    }
    
    public Map<String, Registration> getRegistration() {
        return this.registration;
    }
    
    @PostConstruct
    public void validate() {
        this.getRegistration().values().forEach(this::validateRegistration);
    }
    
    private void validateRegistration(Registration registration) {
        if (!StringUtils.hasText(registration.getClientId())) {
            throw new IllegalStateException("Client id must not be empty.");
        }
    }
    
    /**
     * A single client registration.
     */
    public static class Registration {
        
        private String provider;
        
        /**
         * Client ID for the registration.
         */
        private String clientId;
        
        /**
         * Client secret of the registration.
         */
        private String clientSecret;
        
        /**
         * Client authentication method.
         */
        private String clientAuthenticationMethod;
        
        /**
         * Authorization grant type.
         */
        private String authorizationGrantType;
        
        /**
         * Redirect URI.
         */
        private String redirectUri;
        
        /**
         * Authorization scopes.
         */
        private Set<String> scope;
        
        /**
         * Client name.
         */
        private String clientName;
        
        /**
         * AppID region.
         */
        private String region;
        
        /**
         * Tenant id of AppID
         */
        private String tenantID;
        
        /**
         * version of AppID end point
         */
        private String version;
        
        /**
         * URI that can either be an OpenID Connect discovery endpoint or an OAuth 2.0
         * Authorization Server Metadata endpoint defined by RFC 8414.
         */
        private String issuerUri;
        
        public String getProvider() {
            return this.provider;
        }
        
        public void setProvider(String provider) {
            this.provider = provider;
        }
        
        public String getClientId() {
            return this.clientId;
        }
        
        public void setClientId(String clientId) {
            this.clientId = clientId;
        }
        
        public String getClientSecret() {
            return this.clientSecret;
        }
        
        public void setClientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
        }
        
        public String getClientAuthenticationMethod() {
            return this.clientAuthenticationMethod;
        }
        
        public void setClientAuthenticationMethod(String clientAuthenticationMethod) {
            this.clientAuthenticationMethod = clientAuthenticationMethod;
        }
        
        public String getAuthorizationGrantType() {
            return this.authorizationGrantType;
        }
        
        public void setAuthorizationGrantType(String authorizationGrantType) {
            this.authorizationGrantType = authorizationGrantType;
        }
        
        public String getRedirectUri() {
            return this.redirectUri;
        }
        
        public void setRedirectUri(String redirectUri) {
            this.redirectUri = redirectUri;
        }
        
        public Set<String> getScope() {
            return this.scope;
        }
        
        public void setScope(Set<String> scope) {
            this.scope = scope;
        }
        
        public String getClientName() {
            return this.clientName;
        }
        
        public void setClientName(String clientName) {
            this.clientName = clientName;
        }
        
        public String getRegion() {
            return region;
        }
        
        public void setRegion(String region) {
            this.region = region;
        }
        
        public String getTenantID() {
            return tenantID;
        }
        
        public void setTenantID(String tenantID) {
            this.tenantID = tenantID;
        }
        
        public String getVersion() {
            return version;
        }
        
        public void setVersion(String version) {
            this.version = version;
        }
        
        public String getIssuerUri() {
            return issuerUri;
        }
        
        public void setIssuerUri(String issuerUri) {
            this.issuerUri = issuerUri;
        }
    }
    
    public static class Provider {
        
        /**
         * Authorization URI for the provider.
         */
        private String authorizationUri;
        
        /**
         * Token URI for the provider.
         */
        private String tokenUri;
        
        /**
         * User info URI for the provider.
         */
        private String userInfoUri;
        
        /**
         * User info authentication method for the provider.
         */
        private String userInfoAuthenticationMethod;
        
        /**
         * Name of the attribute that will be used to extract the username from the call
         * to 'userInfoUri'.
         */
        private String userNameAttribute;
        
        /**
         * JWK set URI for the provider.
         */
        private String jwkSetUri;
        
        /**
         * URI that can either be an OpenID Connect discovery endpoint or an OAuth 2.0
         * Authorization Server Metadata endpoint defined by RFC 8414.
         */
        private String issuerUri;
        
        public String getAuthorizationUri() {
            return this.authorizationUri;
        }
        
        public void setAuthorizationUri(String authorizationUri) {
            this.authorizationUri = authorizationUri;
        }
        
        public String getTokenUri() {
            return this.tokenUri;
        }
        
        public void setTokenUri(String tokenUri) {
            this.tokenUri = tokenUri;
        }
        
        public String getUserInfoUri() {
            return this.userInfoUri;
        }
        
        public void setUserInfoUri(String userInfoUri) {
            this.userInfoUri = userInfoUri;
        }
        
        public String getUserInfoAuthenticationMethod() {
            return this.userInfoAuthenticationMethod;
        }
        
        public void setUserInfoAuthenticationMethod(String userInfoAuthenticationMethod) {
            this.userInfoAuthenticationMethod = userInfoAuthenticationMethod;
        }
        
        public String getUserNameAttribute() {
            return this.userNameAttribute;
        }
        
        public void setUserNameAttribute(String userNameAttribute) {
            this.userNameAttribute = userNameAttribute;
        }
        
        public String getJwkSetUri() {
            return this.jwkSetUri;
        }
        
        public void setJwkSetUri(String jwkSetUri) {
            this.jwkSetUri = jwkSetUri;
        }
        
        public String getIssuerUri() {
            return this.issuerUri;
        }
        
        public void setIssuerUri(String issuerUri) {
            this.issuerUri = issuerUri;
        }
    }
}
