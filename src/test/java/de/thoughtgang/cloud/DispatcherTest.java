package de.thoughtgang.cloud;

import de.thoughtgang.cloud.base.Message;
import de.thoughtgang.cloud.core.Dispatcher;
import de.thoughtgang.cloud.message.TextMessage;
import de.thoughtgang.cloud.outlet.FileOutlet;
import de.thoughtgang.cloud.util.ConfigurationError;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.nio.file.FileSystems;
import java.util.Properties;

import static de.thoughtgang.cloud.outlet.FileOutlet.HEADER_OUTPUT_FILENAME;
import static de.thoughtgang.cloud.outlet.FileOutlet.INIT_OUTPUT_DIR;

@QuarkusTest
public class DispatcherTest {

    @Inject
    private Dispatcher dispatcher;
    private static final String TEST_OUTPUT_DIR = "target/output";

   /* @BeforeEach
    public void beforeEach() throws ConfigurationError {

        Properties properties = new Properties();
        properties.setProperty(INIT_OUTPUT_DIR, TEST_OUTPUT_DIR);
        this.fileOutlet = new FileOutlet();
        this.fileOutlet.init(properties);

    }
    */

    @Test
    public void testWritingFile() throws ConfigurationError {

        Message message = getTextMessage();
        message = dispatcher.dispatch(message);
        String fileName = message.getHeaders().get(HEADER_OUTPUT_FILENAME);
        Assertions.assertNotNull(fileName);
        Object test = FileSystems.getDefault().getPath(TEST_OUTPUT_DIR).resolve(fileName).toFile();
        Assertions.assertTrue(FileSystems.getDefault().getPath(TEST_OUTPUT_DIR).resolve(fileName).toFile().exists());

    }

    public Message getTextMessage() {

        return new TextMessage("Hallo, Welt!");

    }

}
