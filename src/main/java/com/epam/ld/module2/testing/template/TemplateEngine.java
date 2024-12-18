package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;

/**
 * The type Template engine.
 */
public class TemplateEngine {
    /**
     * Generate message string.
     *
     * @param template the template
     * @param client   the client
     * @return the string
     */
    public String generateMessage(Template template, Client client) {
        String message = template.getContent();
        String name = client.getName();
        String subject = client.getSubject();

        if (name == null || name.isEmpty() || subject == null || subject.isEmpty()) {
            throw new IllegalArgumentException("Placeholder values must not be null or empty");
        }

        message = message.replace("#{name}", name);
        message = message.replace("#{subject}", subject);
        return message;
    }
}
