package de.thoughtgang.cloud.core;

import de.thoughtgang.cloud.base.Message;
import de.thoughtgang.cloud.outlet.FileOutlet;
import de.thoughtgang.cloud.util.ConfigurationError;

import javax.enterprise.context.ApplicationScoped;
import java.util.Properties;

import static de.thoughtgang.cloud.outlet.FileOutlet.INIT_OUTPUT_DIR;

@ApplicationScoped
public class Dispatcher {

    FileOutlet fileOutlet; // wird später konfigurierbar

    public Message dispatch(Message message) throws ConfigurationError {

        Properties properties = new Properties();
        properties.setProperty(INIT_OUTPUT_DIR, "/tmp");
        this.fileOutlet = new FileOutlet();
        this.fileOutlet.init(properties);
        fileOutlet.receive(message);

        return message;
    }
}
