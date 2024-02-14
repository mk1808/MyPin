package com.mypin.users.config;

import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakAdminConnectionConfig {

	@Value("${keycloak.admin-connection.server-url}")
	private String serverUrl;
	@Value("${keycloak.admin-connection.realm}")
	private String realm;
	@Value("${keycloak.admin-connection.username}")
	private String username;
	@Value("${keycloak.admin-connection.password}")
	private String password;
	@Value("${keycloak.admin-connection.clientId}")
	private String clientId;
	
	@Bean
	public Keycloak keycloak() {
		return KeycloakBuilder
				.builder()
				.serverUrl(serverUrl)
				.realm(realm)
				.grantType(OAuth2Constants.PASSWORD)
				.username(username)
				.password(password)
				.clientId(clientId)
				.resteasyClient(
						new ResteasyClientBuilderImpl()
						.connectionPoolSize(10)
						.build())
				.build();
	}
}
