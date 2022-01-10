/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.thoughtgang.cloud.message;

/**
 *
 * @author fhossfel
 */
public class TextMessage extends AbstractMessage {
    
    private String payload;

    public TextMessage(String payload) {
        
        this.payload = payload;
    }

    @Override
    public String getPayload() {
        return payload;
    }
    
}
