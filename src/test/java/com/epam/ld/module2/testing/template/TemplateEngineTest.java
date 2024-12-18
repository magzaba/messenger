package com.epam.ld.module2.testing.template;
import com.epam.ld.module2.testing.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemplateEngineTest {
    private TemplateEngine templateEngine;
    private Template template;
    private Client client;

    @BeforeEach
    public void setUp() {
        templateEngine = new TemplateEngine();
        template = new Template();
        client = new Client();
    }

    @Test
    public void testGenerateMessage_ReplacesPlaceholdersCorrectly() {
        // Arrange
        String templateContent = "Dear #{name}, your subject is #{subject}.";
        template.setContent(templateContent);
        client.setName("John");
        client.setSubject("Java");

        // Act
        String result = templateEngine.generateMessage(template, client);

        // Assert
        String expectedMessage = "Dear John, your subject is Java.";
        assertEquals(expectedMessage, result);
    }
}
