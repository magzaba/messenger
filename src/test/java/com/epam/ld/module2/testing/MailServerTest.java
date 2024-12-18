package com.epam.ld.module2.testing;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MailServerTest {
    @Test
    public void testSend_CallsSendWithCorrectParameters() {

        MailServer mailServer = new MailServer();
        String addresses = "test@example.com";
        String messageContent = "Hello, World!";
        MailServer spyMailServer = mock(MailServer.class);


        spyMailServer.send(addresses, messageContent);


        verify(spyMailServer).send(addresses, messageContent);
    }
}
