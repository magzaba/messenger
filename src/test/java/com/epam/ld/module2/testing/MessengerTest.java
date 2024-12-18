package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class MessengerTest {
    @Test
    public void testSendMessage_UsesMailServerCorrectly() {
        // Arrange
        MailServer mailServer = mock(MailServer.class);
        TemplateEngine templateEngine = new TemplateEngine();
        Messenger messenger = new Messenger(mailServer, templateEngine);
        Client client = new Client();
        client.setAddresses("john@example.com");
        Template template = new Template();
        template.setContent("Hello, #{name}");
        client.setName("John");

        // Act
        messenger.sendMessage(client, template);

        // Assert
        verify(mailServer).send("john@example.com", "Hello, John");
    }
}