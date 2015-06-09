/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.developers.monitor.persistence.service.exceptions;

/**
 *
 * @author Kamciak
 */
public class UserAlreadyExistException extends Exception {

    public UserAlreadyExistException() { }

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
