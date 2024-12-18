package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;

public class AddressesPlaceholderStrategy implements PlaceholderStrategy {
    @Override
    public String getValue(Client client) {
        return client.getAddresses();
    }
}
