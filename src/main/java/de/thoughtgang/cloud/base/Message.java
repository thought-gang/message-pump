/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.thoughtgang.cloud.base;

import java.util.Map;

/**
 *
 * @author fhossfel
 */
public interface Message {
    
    public Map<String, String> getHeaders();
    
    public Object getPayload();
    
}
