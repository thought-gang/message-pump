/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.thoughtgang.cloud.util;

/**
 *
 * @author fhossfel
 */
public class OutletException extends RuntimeException {

    public OutletException(String message) {
        super(message);
    }

    public OutletException(String message, Throwable cause) {
        super(message, cause);
    }

    
}
