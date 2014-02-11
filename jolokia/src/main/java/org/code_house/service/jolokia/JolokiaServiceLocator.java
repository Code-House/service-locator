package org.code_house.service.jolokia;

import org.code_house.service.core.AbstractServiceLocator;
import org.code_house.service.core.AdapterProvider;
import org.code_house.service.core.ConnectionManager;
import org.jolokia.client.J4pClient;

import javax.management.MBeanServer;
import java.util.List;

public class JolokiaServiceLocator extends AbstractServiceLocator<J4pClient> {
    public JolokiaServiceLocator(ConnectionManager<J4pClient> connectionManager, List<AdapterProvider<?, J4pClient>> adapterProviders) {
        super(connectionManager, adapterProviders);
    }
}
