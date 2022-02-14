package de.thoughtgang.cloud.core;

import de.thoughtgang.cloud.outlet.FileOutlet;
import de.thoughtgang.cloud.util.ConfigurationError;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DispatcherProducer {

    @Produces
    @ApplicationScoped
    public Dispatcher produce() throws IOException, ConfigurationError {

        Properties properties = new Properties();
        try (InputStream is = DispatcherProducer.class.getClassLoader().getResourceAsStream("message-pump.properties")) {

            properties.load(is);
        }

        FileOutlet fileOutlet = new FileOutlet();
        fileOutlet.init(properties);
        return new Dispatcher(fileOutlet);

    }
}
