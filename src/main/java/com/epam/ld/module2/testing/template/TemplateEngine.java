package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Template engine.
 */
public class TemplateEngine {

    private Map<String, PlaceholderStrategy> strategies;

    public TemplateEngine() {
        strategies = new HashMap<>();
        strategies.put("name", new NamePlaceholderStrategy());
        strategies.put("subject", new SubjectPlaceholderStrategy());
        strategies.put("addresses", new AddressesPlaceholderStrategy());
    }

    /**
     * Generate message string.
     *
     * @param template the template
     * @param client   the client
     * @return the string
     */
    public String generateMessage(Template template, Client client) {
        String message = template.getContent();
        Pattern pattern = Pattern.compile("#\\{(\\w+)\\}");
        Matcher matcher = pattern.matcher(message);

        while (matcher.find()) {
            String key = matcher.group(1);
            PlaceholderStrategy strategy = strategies.get(key);
            if (strategy == null) {
                continue; // Ignore placeholders without strategies
            }
            String value = strategy.getValue(client);
            if (value == null || value.isEmpty()) {
                throw new IllegalArgumentException("Placeholder values must not be null or empty");
            }
            message = message.replace("#{" + key + "}", value);
        }
        return message;
    }
}
