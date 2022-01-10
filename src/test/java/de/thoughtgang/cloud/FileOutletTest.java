/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.thoughtgang.cloud;

import de.thoughtgang.cloud.base.Message;
import de.thoughtgang.cloud.message.TextMessage;
import de.thoughtgang.cloud.outlet.FileOutlet;
import de.thoughtgang.cloud.util.ConfigurationError;
import java.util.Properties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author fhossfel
 */
public class FileOutletTest {
    
    private FileOutlet fileOutlet;
    
    @BeforeEach
    public void before() {
        
        this.fileOutlet = new FileOutlet();
        
    }
    
    
    @Test
    public void testConfigurationError() throws ConfigurationError {
        
        Properties properties = new Properties();
        Assertions.assertThrows(ConfigurationError.class, () -> {this.fileOutlet.init(properties); });

    }
    
    public Message getTextMessage() {
        
        Message message = new TextMessage("Hallo, Welt!");
        
        return message;
        
        
    }
    
}
