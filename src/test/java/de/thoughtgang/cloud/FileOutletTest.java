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

import java.nio.file.FileSystems;
import java.util.Properties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static de.thoughtgang.cloud.outlet.FileOutlet.HEADER_OUTPUT_FILENAME;
import static de.thoughtgang.cloud.outlet.FileOutlet.INIT_OUTPUT_DIR;

/**
 *
 * @author fhossfel
 */
public class FileOutletTest {
    
    private FileOutlet fileOutlet;
    private static final String TEST_OUTPUT_DIR = "target/output";
    
    @BeforeEach
    public void beforeEach() throws ConfigurationError {

        Properties properties = new Properties();
        properties.setProperty(INIT_OUTPUT_DIR, TEST_OUTPUT_DIR);
        this.fileOutlet = new FileOutlet();
        this.fileOutlet.init(properties);

    }
    
    
    @Test
    public void testConfigurationError() {
        
        Properties properties = new Properties();
        FileOutlet fileOutlet = new FileOutlet();
        Assertions.assertThrows(ConfigurationError.class, () -> fileOutlet.init(properties));


    }

    @Test
    public void testWritingFile() {

        Message message = getTextMessage();
        message = fileOutlet.receive(message);
        String fileName = message.getHeaders().get(HEADER_OUTPUT_FILENAME);
        Assertions.assertNotNull(fileName);
        Object test = FileSystems.getDefault().getPath(TEST_OUTPUT_DIR).resolve(fileName).toFile();
        Assertions.assertTrue(FileSystems.getDefault().getPath(TEST_OUTPUT_DIR).resolve(fileName).toFile().exists());

    }
    
    public Message getTextMessage() {
        
         return new TextMessage("Hallo, Welt!");
        
    }
    
}
