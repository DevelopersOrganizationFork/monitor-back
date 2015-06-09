package org.developers.monitor.rest.authorization;

import org.developers.monitor.persistence.Users;
import org.developers.monitor.rest.dto.UserDto;

/**
 * Created by sebastian.alberski on 2015-06-09.
 */
public class Authorization {
    private String requestLogin = "";
    private String requestPassword = "";
    private String expectedLogin = "";
    private String expectedPassword = "";
    
    public Authorization(UserDto userDto, Users users) {
        this.requestLogin = userDto.login;
        this.requestPassword = userDto.password;
        this.expectedLogin = users.getUserName();
        this.expectedPassword = users.getUserPassword();
    }
    
    public boolean verify() {
        return (requestLogin.equals(expectedLogin) && requestPassword.equals(expectedPassword));
    }
}
