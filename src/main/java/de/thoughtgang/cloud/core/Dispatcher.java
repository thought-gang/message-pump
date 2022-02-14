package de.thoughtgang.cloud.core;

import de.thoughtgang.cloud.base.Message;
import de.thoughtgang.cloud.outlet.FileOutlet;
import de.thoughtgang.cloud.util.ConfigurationError;

import java.util.Properties;

import static de.thoughtgang.cloud.outlet.FileOutlet.INIT_OUTPUT_DIR;

public class Dispatcher {

    private final FileOutlet fileOutlet; // wird später konfigurierbar

    public Dispatcher(FileOutlet fileOutlet) {
        this.fileOutlet = fileOutlet;
    }

    public Message dispatch(Message message) throws ConfigurationError {

        fileOutlet.receive(message);

        return message;
    }
}
