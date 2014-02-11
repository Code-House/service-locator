package org.code_house.service.core;

import org.code_house.service.api.ServiceLocator;
import org.code_house.service.api.ServicePointer;

import javax.management.MBeanServer;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AbstractServiceLocator<C> implements ServiceLocator {

    private final ConnectionManager<C> connectionManager;
    private final List<AdapterProvider<?, C>> adapterProviders;

    public AbstractServiceLocator(ConnectionManager<C> connectionManager, List<AdapterProvider<?, C>> adapterProviders) {
        this.connectionManager = connectionManager;
        this.adapterProviders = adapterProviders;
    }

    @Override
    public <T, P extends Serializable> Set<ServicePointer<T, P>> lookup(Class<T> type) {
        Set<ServicePointer<T, P>> pointers = new HashSet<ServicePointer<T, P>>();
        for (C connection : connectionManager.getConnections()) {
            for (AdapterProvider<?, C> provider : adapterProviders) {
                if (provider.isSupported(type)) {
                    pointers.add((ServicePointer<T, P>) provider.createAdapter(connection));
                }
            }
        }

        return pointers;
    }

}
