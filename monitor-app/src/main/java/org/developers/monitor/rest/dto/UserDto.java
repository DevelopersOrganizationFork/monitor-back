package org.developers.monitor.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by sebastian.alberski on 2015-06-09.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    public String login;
    public String password;
}
