package com.spi.service.one;

import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.provider.ProviderConfigurationBuilder;
import org.keycloak.storage.UserStorageProviderFactory;
import java.util.List;

public class ServiceOneStorageProviderFactory implements UserStorageProviderFactory<ServiceOneStorageProvider> {

    @Override
    public ServiceOneStorageProvider create(KeycloakSession session, ComponentModel model) {
        return new ServiceOneStorageProvider(session, model);
    }

    @Override
    public String getId() {
        return "service-one-login";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return ProviderConfigurationBuilder.create()
                .property("url", "URL", "Lorem ipsum dolor sit amet", ProviderConfigProperty.STRING_TYPE, "", null)
                .build();
    }
}
