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

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistration.Builder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;

/**
 * AppID OAuth2 Providers that can be used to create pre-configured with sensible defaults.
 *
 */
public enum AppIDOAuth2Provider {

    DALLAS {
        @Override
        public Builder getBuilder(String registrationId, AppIDOAuth2ConfigurationProperties.Registration properties) {
            return getBuilder(registrationId, APPID_OAUTH_URL_DALLAS, properties);
        }
    },

    SYDNEY {
        @Override
        public Builder getBuilder(String registrationId, AppIDOAuth2ConfigurationProperties.Registration properties) {
            return getBuilder(registrationId, APPID_OAUTH_URL_SYDNEY, properties);
        }
    },

    FRANKFURT {
        @Override
        public Builder getBuilder(String registrationId, AppIDOAuth2ConfigurationProperties.Registration properties) {
            return getBuilder(registrationId, APPID_OAUTH_URL_FRANKFURT, properties);
        }
    },

    LONDON {
        @Override
        public Builder getBuilder(String registrationId, AppIDOAuth2ConfigurationProperties.Registration properties) {
            return getBuilder(registrationId, APPID_OAUTH_URL_LONDON, properties);
        }
    },

    TOKYO {
        @Override
        public Builder getBuilder(String registrationId, AppIDOAuth2ConfigurationProperties.Registration properties) {
            return getBuilder(registrationId, APPID_OAUTH_URL_TOKYO, properties);
        }
    };

    private static final String APPID_OAUTH_URL_DALLAS = "https://us-south.appid.cloud.ibm.com/oauth/";
    private static final String APPID_OAUTH_URL_SYDNEY = "https://au-syd.appid.cloud.ibm.com/oauth/";
    private static final String APPID_OAUTH_URL_FRANKFURT = "https://eu-de.appid.cloud.ibm.com/oauth/";
    private static final String APPID_OAUTH_URL_LONDON = "https://eu-gb.appid.cloud.ibm.com/oauth/";
    private static final String APPID_OAUTH_URL_TOKYO = "https://jp-tok.appid.cloud.ibm.com/oauth/";
    private static final String DEFAULT_APPID_VERSION = "4";
    private static final String DEFAULT_REDIRECT_URL = "{baseUrl}/{action}/oauth2/code/{registrationId}";


    public Builder getBuilder(String registrationId, String appIDURL, AppIDOAuth2ConfigurationProperties.Registration properties) {
        Set<String> scope = new HashSet<String>();
        scope.add("openid");
        String oAuthServerUri = getOAuthServerUri(appIDURL, properties.getVersion(),
                properties.getTenantID());
        AuthorizationGrantType authorizationGrantType = AuthorizationGrantType.AUTHORIZATION_CODE;
        if (properties.getAuthorizationGrantType() != null) {
            authorizationGrantType = new AuthorizationGrantType(properties.getAuthorizationGrantType());
        }

        Builder builder = ClientRegistration.withRegistrationId(registrationId);
        builder.clientId(properties.getClientId());
        builder.clientSecret(properties.getClientSecret());
        builder.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC);
        builder.authorizationGrantType(authorizationGrantType);
        builder.redirectUri(properties.getRedirectUri() != null ? properties.getRedirectUri() : DEFAULT_REDIRECT_URL);
        builder.scope(properties.getScope() != null ? properties.getScope() : scope);
        builder.authorizationUri(oAuthServerUri + "/authorization");
        builder.tokenUri(oAuthServerUri + "/token");
        builder.userInfoUri(oAuthServerUri + "/userinfo");
        builder.jwkSetUri(oAuthServerUri + "/publickeys");
        builder.userNameAttributeName(IdTokenClaimNames.SUB);
        builder.clientName("AppID");
        return builder;
    }

    public String getOAuthServerUri(String appIDURL, String version, String tenantID) {
        return new StringBuilder(appIDURL)
                .append("v" + (version != null ? version : DEFAULT_APPID_VERSION) + "/")
                .append(tenantID).toString();
    }

    /**
     * Create a new pre-configured with provider defaults.
     */
    public abstract Builder getBuilder(String registrationId, AppIDOAuth2ConfigurationProperties.Registration properties);
}
