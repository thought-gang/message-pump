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
public class ByteMessage extends AbstractMessage {
    
    private byte[] payload;

    public ByteMessage(byte[] payload) {
        super();
        this.payload = payload;
    }

    @Override
    public byte[] getPayload() {
        return payload;
    }
    
}
