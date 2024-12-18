package com.epam.ld.module2.testing.template;
import com.epam.ld.module2.testing.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void testGenerateMessage_ThrowsExceptionWhenPlaceholderValueMissing() {
        // Arrange
        String templateContent = "Dear #{name}, your subject is #{subject}.";
        template.setContent(templateContent);
        client.setName("John");
        client.setSubject(""); // Empty subject should trigger the exception

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            templateEngine.generateMessage(template, client);
        });

        String expectedMessage = "Placeholder values must not be null or empty";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testGenerateMessage_IgnoresUnusedPlaceholders() {
        // Arrange
        String templateContent = "Dear #{name}, we have no data on #{unused}.";
        template.setContent(templateContent);
        client.setName("John");
        client.setSubject("Java"); // 'subject' is not used in the template

        // Act
        String result = templateEngine.generateMessage(template, client);

        // Assert
        String expectedMessage = "Dear John, we have no data on #{unused}.";
        assertEquals(expectedMessage, result);
    }

    @Test
    public void testGenerateMessage_IncludesAddressesPlaceholder() {
        // Arrange
        String templateContent = "To: #{addresses}";
        template.setContent(templateContent);
        client.setAddresses("john@example.com");

        // Act
        String result = templateEngine.generateMessage(template, client);

        // Assert
        String expectedMessage = "To: john@example.com";
        assertEquals(expectedMessage, result);
    }


}
