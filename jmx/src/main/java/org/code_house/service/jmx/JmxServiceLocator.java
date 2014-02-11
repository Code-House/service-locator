package org.code_house.service.jmx;

import org.code_house.service.core.AbstractServiceLocator;
import org.code_house.service.core.AdapterProvider;
import org.code_house.service.core.ConnectionManager;

import javax.management.MBeanServer;
import javax.management.MBeanServerConnection;
import java.util.List;

public class JmxServiceLocator extends AbstractServiceLocator<MBeanServerConnection> {
    public JmxServiceLocator(ConnectionManager<MBeanServerConnection> connectionManager, List<AdapterProvider<?, MBeanServerConnection>> adapterProviders) {
        super(connectionManager, adapterProviders);
    }
}
