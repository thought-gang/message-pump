/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.thoughtgang.cloud.base;

import de.thoughtgang.cloud.util.ConfigurationError;
import java.util.Properties;

/**
 *
 * @author fhossfel
 */
public interface Outlet {
    
    public void init(Properties properties) throws ConfigurationError;
    
    public Message receive(Message message);
    
}
