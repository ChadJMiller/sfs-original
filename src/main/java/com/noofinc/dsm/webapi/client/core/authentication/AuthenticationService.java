package com.noofinc.dsm.webapi.client.core.authentication;

public interface AuthenticationService {

    LoginInformation login(String username, String password, String session);

    void logout(LoginInformation loginInformation);
}
