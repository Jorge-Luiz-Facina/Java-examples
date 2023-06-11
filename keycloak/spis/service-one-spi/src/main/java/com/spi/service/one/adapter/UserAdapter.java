package com.spi.service.one.adapter;

import com.spi.service.one.client.dto.InfoResponseDTO;
import org.keycloak.common.util.MultivaluedHashMap;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.adapter.AbstractUserAdapterFederatedStorage;
import java.util.*;

public class UserAdapter extends AbstractUserAdapterFederatedStorage {

    public static final String NOT_IMPLEMENTED = "Not implemented";
    static final String REAL_USERNAME_ATTRIBUTE = "realUsername";

    private final String keycloakId;
    private InfoResponseDTO infoResponseDTO;

    public UserAdapter(KeycloakSession session, RealmModel realm, ComponentModel model, InfoResponseDTO infoResponseDTO) {
        super(session, realm, model);
        this.keycloakId = StorageId.keycloakId(model, infoResponseDTO.getLogin());
        this.infoResponseDTO = infoResponseDTO;

        setAttribute("login", Arrays.asList(infoResponseDTO.getLogin()));
        setAttribute("detail", Arrays.asList(infoResponseDTO.getDetail()));
//        setAttribute("list", (ObjectUtil.getObjectJson(list)));
    }

    @Override
    public String getId() {
        return keycloakId;
    }

    @Override
    public String getUsername() {
        return infoResponseDTO.getLogin();
    }

    @Override
    public void setUsername(String username) {

    }

    @Override
    public String getEmail() {
        return NOT_IMPLEMENTED;
    }

    @Override
    public void setEmail(String email) {

    }

    @Override
    public String getFirstName() {
        return NOT_IMPLEMENTED;
    }

    @Override
    public void setFirstName(String firstName) {

    }

    @Override
    public String getLastName() {
        return NOT_IMPLEMENTED;
    }

    @Override
    public void setLastName(String lastName) {

    }

    @Override
    public void setSingleAttribute(String name, String value) {
        if (!REAL_USERNAME_ATTRIBUTE.equals(name)) {
            super.setSingleAttribute(name, value);
        }
    }

    @Override
    public void removeAttribute(String name) {
        if (!REAL_USERNAME_ATTRIBUTE.equals(name)) {
            super.removeAttribute(name);
        }
    }

    @Override
    public void setAttribute(String name, List<String> values) {
        if (!REAL_USERNAME_ATTRIBUTE.equals(name)) {
            super.setAttribute(name, values);
        }
    }

    @Override
    public String getFirstAttribute(String name) {
        if (REAL_USERNAME_ATTRIBUTE.equals(name)) {
            return infoResponseDTO.getLogin();
        } else {
            return super.getFirstAttribute(name);
        }
    }

    @Override
    public List<String> getAttribute(String name) {
        if (REAL_USERNAME_ATTRIBUTE.equals(name)) {
            return Collections.singletonList(infoResponseDTO.getLogin());
        } else {
            return super.getAttribute(name);
        }
    }

    @Override
    public Map<String, List<String>> getAttributes() {
        Map<String, List<String>> attrs = super.getAttributes();
        MultivaluedHashMap<String, String> all = new MultivaluedHashMap<>();
        all.putAll(attrs);
        all.add(REAL_USERNAME_ATTRIBUTE, infoResponseDTO.getLogin());
        return all;
    }

}
