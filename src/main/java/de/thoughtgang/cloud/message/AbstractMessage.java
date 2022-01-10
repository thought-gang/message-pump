/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.thoughtgang.cloud.message;

import de.thoughtgang.cloud.base.Message;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author fhossfel
 */
public abstract class AbstractMessage implements Message {
    
    private Map<String, String> headers = new HashMap<>();

    @Override
    public Map<String, String> getHeaders() {
        //Fixme: Immutable?
        return headers;
    }

    
}
