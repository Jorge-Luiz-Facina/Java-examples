package com.spi.service.two;


import com.spi.service.two.client.ExternalClient;
import com.spi.service.two.client.dto.InfoResponseDTO;
import com.spi.service.two.client.dto.UserLoginResponseDTO;
import com.spi.service.two.adapter.UserAdapter;
import com.spi.service.two.enums.ExceptionEnum;
import com.spi.service.two.exception.CustomException;
import com.spi.service.two.log.LoggingFilter;
import org.apache.http.HttpStatus;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.credential.CredentialModel;
import org.keycloak.models.*;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.user.UserLookupProvider;
import org.keycloak.storage.user.UserQueryProvider;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.logging.Logger;
import java.util.logging.Level;

public class ServiceTwoStorageProvider
        implements UserStorageProvider, UserLookupProvider, UserQueryProvider, CredentialInputValidator {

    private final KeycloakSession session;
    private final ComponentModel model;
    private UserAdapter userAdapter;
    private static final Logger LOG
            = Logger.getLogger(ServiceTwoStorageProvider.class.getName());

    public ServiceTwoStorageProvider(KeycloakSession session, ComponentModel model) {
        this.session = session;
        this.model = model;
    }

    @Override
    public UserModel getUserById(String id, RealmModel realm) {
        String[] parts = id.split(":");
        return getUserByUsername(parts[2], realm);
    }

    @Override
    public UserModel getUserByUsername(String username, RealmModel realm) {
        if (Objects.isNull(userAdapter) || !userAdapter.getUsername().equals(username)) {
            Client client = client();
            try (Response response = externalClient(client).getUserByLogin(username)) {
                validateStatus(response);
                InfoResponseDTO infoResponseDTO = response.readEntity(InfoResponseDTO.class);
                if (Objects.isNull(infoResponseDTO) || Objects.isNull(infoResponseDTO.getLogin())) {
                    throw new CustomException(ExceptionEnum.NON_ACTIVE_USER.name());
                }
                userAdapter = new UserAdapter(session, realm, model, infoResponseDTO);
            } finally {
                client.close();
            }
        }
        return userAdapter;
    }

    private void updateCacheByUser(InfoResponseDTO infoResponseDTO, UserModel user) {
        boolean refreshUser = true;
        if (Objects.isNull(user) || refreshUser) {
            LOG.log(Level.INFO, "update cache by user: " + user.getUsername());
            user.setFederationLink(this.model.getId());
        }
    }

    @Override
    public UserModel getUserByEmail(String email, RealmModel realm) {
        return getUserByUsername(email, realm);
    }

    @Override
    public boolean supportsCredentialType(String credentialType) {
        return CredentialModel.PASSWORD.equals(credentialType);
    }

    @Override
    public boolean isConfiguredFor(RealmModel realm, UserModel user, String credentialType) {
        return supportsCredentialType(credentialType);
    }

    @Override
    public boolean isValid(RealmModel realm, UserModel user, CredentialInput input) {
        Client client = client();
        Response response = externalClient(client).getUserByLogin(user.getUsername());
        UserLoginResponseDTO userLoginResponseDTO = new UserLoginResponseDTO();
        UserCredentialModel credential = (UserCredentialModel) input;
        userLoginResponseDTO.setUserName(user.getUsername());
        userLoginResponseDTO.setPassword(credential.getValue());
        Response responseLogin = externalClient(client).login(userLoginResponseDTO);
        InfoResponseDTO infoResponseDTO = response.readEntity(InfoResponseDTO.class);
        boolean isOk = false;
        try {
            isOk = responseLogin.getStatus() == HttpStatus.SC_OK && response.getStatus() == HttpStatus.SC_OK;
            if (isOk) {
                LOG.log(Level.INFO, "login sucess");
                updateCacheByUser(infoResponseDTO, user);
            }
        } catch (Exception exception) {
            LOG.log(Level.INFO, "login error ");
        } finally {
            response.close();
            responseLogin.close();
            client.close();
        }
        return isOk;
    }

    @Override
    public void preRemove(RealmModel realm) {
    }

    @Override
    public void preRemove(RealmModel realm, GroupModel group) {
    }

    @Override
    public void preRemove(RealmModel realm, RoleModel role) {
    }

    @Override
    public void close() {
    }

    @Override
    public int getUsersCount(RealmModel realm) {
        int size = 0;
        Client client = client();
        Response response = externalClient(client).getAll();
        try {
            validateStatus(response);
            List<InfoResponseDTO> infoResponseDTOS = response.readEntity(new GenericType<List<InfoResponseDTO>>() {
            });
            size = infoResponseDTOS.size();
        } finally {
            response.close();
            client.close();
        }
        return size;
    }

    @Override
    public List<UserModel> getUsers(RealmModel realm) {
        List<UserModel> userModels = new ArrayList<>();
//        Client client = client();
//        Response response = serviceOneClient(client).getAll();
//        try {
//            validateStatus(response);
//            List<InfoResponseDTO> infoResponseDTOS = response.readEntity(new GenericType<List<InfoResponseDTO>>() {
//            });
//            userModels = infoResponseDTOS.stream().map(info -> new UserAdapter(session, realm, model, info)).collect(Collectors.toList());
//        } finally {
//            response.close();
//            client.close();
//        }
        return userModels;
    }

    @Override
    public List<UserModel> getUsers(RealmModel realm, int firstResult, int maxResults) {
        return getUsers(realm);
    }

    @Override
    public List<UserModel> searchForUser(String search, RealmModel realm) {
        if (search.isEmpty()) {
            return getUsers(realm);
        }
        return search(search, realm);
    }

    @Override
    public List<UserModel> searchForUser(String search, RealmModel realm, int firstResult, int maxResults) {
        if (search.isEmpty()) {
            return getUsers(realm);
        }
        return search(search, realm);
    }

    @Override
    public List<UserModel> searchForUser(Map<String, String> params, RealmModel realm) {
        if (params.isEmpty()) {
            return getUsers(realm);
        }
        return Collections.emptyList();
    }

    @Override
    public List<UserModel> searchForUser(Map<String, String> params, RealmModel realm, int firstResult, int maxResults) {
        if (params.isEmpty()) {
            return getUsers(realm);
        }
        return Collections.emptyList();
    }

    @Override
    public List<UserModel> getGroupMembers(RealmModel realm, GroupModel group, int firstResult, int maxResults) {
        return Collections.emptyList();
    }

    @Override
    public List<UserModel> getGroupMembers(RealmModel realm, GroupModel group) {
        return Collections.emptyList();
    }

    @Override
    public List<UserModel> searchForUserByUserAttribute(String attrName, String attrValue, RealmModel realm) {
        return Collections.emptyList();
    }

    private List<UserModel> search(String login, RealmModel realm) {
        List<UserModel> userModels = new ArrayList<>();
//        Client client = client();
//        Response response = serviceOneClient(client).getAllByLogin(login);
//        try {
//            validateStatus(response);
//            List<InfoResponseDTO> infoResponseDTOS = response.readEntity(new GenericType<List<InfoResponseDTO>>() {
//            });
//            userModels = infoResponseDTOS.stream().map(info -> new UserAdapter(session, realm, model, info)).collect(Collectors.toList());
//        } finally {
//            response.close();
//            client.close();
//        }
        return userModels;
    }

    private Client client() {
        Client client = ClientBuilder.newClient();
        client.register(new LoggingFilter());
        return client;
    }

    private ExternalClient externalClient(Client client) {
        ExternalClient externalClient = ((ResteasyClient) client).target(model.get("url")).proxy(ExternalClient.class);
        return externalClient;
    }

    private void validateStatus(Response response) {
        if (response.getStatus() > 299 || 200 > response.getStatus()) {
            LOG.log(Level.INFO, "external error : " + response.readEntity(String.class));
            throw new CustomException(ExceptionEnum.EXTERNAL_ERROR.name());
        }
    }

}