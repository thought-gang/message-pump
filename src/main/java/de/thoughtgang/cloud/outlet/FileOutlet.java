package de.thoughtgang.cloud.outlet;

import de.thoughtgang.cloud.util.ConfigurationError;
import de.thoughtgang.cloud.base.Message;
import de.thoughtgang.cloud.base.Outlet;
import de.thoughtgang.cloud.message.ByteMessage;
import de.thoughtgang.cloud.message.TextMessage;
import de.thoughtgang.cloud.util.OutletException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.UUID;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fhossfel
 */
public class FileOutlet implements Outlet {
    
    public static final String INIT_OUTPUT_DIR = "outlet.output.dir";
    public static final String HEADER_OUTPUT_FILENAME = "Filename";
    
    private Path outputDir;

    @Override
    public void init(Properties properties) throws ConfigurationError {
        String path = properties.getProperty(INIT_OUTPUT_DIR);
        if (path == null) {
            throw new ConfigurationError(String.format("%s need a config parameter %s", this.getClass().getName(), INIT_OUTPUT_DIR));
        }
        this.outputDir = Paths.get(path);
        outputDir.toFile().mkdirs();
    }

    @Override
    public Message receive(Message message) {
        
        String filename = getFilename(message);
        File file = new File(this.outputDir.toFile(), filename);
        
        if (message instanceof TextMessage) {
        
            try (FileWriter fileWriter = new FileWriter(file)) {
               
                fileWriter.write(((TextMessage)message).getPayload());
                
            } catch (IOException ex) {
                
                throw new OutletException(String.format("%s could not write message into directory %s: %s", this.getClass().getSimpleName(), this.outputDir.normalize().toAbsolutePath().toString(), ex.getMessage()), ex);
            
            }
            
        } else if (message instanceof ByteMessage) {
            
            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                
                outputStream.write(((ByteMessage)message).getPayload());
            
            } catch (IOException ex) {
                
                throw new OutletException(String.format("%s could not write message into directory %s: %s", this.getClass().getSimpleName(), this.outputDir.normalize().toAbsolutePath().toString(), ex.getMessage()), ex);
            
            }
            
        } else {
                
            throw new OutletException(String.format("%s could not handle unknown message type %s", this.getClass().getSimpleName(), message.getClass().getName()));
                
        }
        
        message.getHeaders().put(HEADER_OUTPUT_FILENAME, filename);
        
        return message;
        
    }
    
    private String getFilename(Message message) {
        
        String filename = UUID.randomUUID().toString();
        String extension = getExtension(message);
        
        return String.format("%s.%s", filename, extension);
        
    }

    private String getExtension(Message message) {
        return message instanceof TextMessage ? "txt" : "bin";
    }


    
}
