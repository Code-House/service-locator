/*
 * Copyright (C) 2014 Code-House, Lukasz Dywicki.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.code_house.service.core;

import com.google.common.base.Optional;
import org.code_house.service.api.ServiceLocator;
import org.code_house.service.api.ServicePointer;
import org.code_house.service.api.WrapperConnection;

import javax.management.MBeanServerConnection;
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

    public <T> Set<ServicePointer<T>> lookup(Class<T> type) {
        Set<ServicePointer<T>> pointers = new HashSet<ServicePointer<T>>();
        for (WrapperConnection<C> connection : connectionManager.getConnections()) {
            for (AdapterProvider<?, C> provider : adapterProviders) {
                if (provider.isSupported(type)) {
                    // let compiler catch P type parameter.
                    Set<ServicePointer<T>> adapters = ((AdapterProvider<T, C>) provider).createAdapters(connection);
                    pointers.addAll(adapters);
                }
            }
        }

        return pointers;
    }

    public <T> Optional<ServicePointer<T>> lookup(ServicePointer<T> serviceId) {
        for (WrapperConnection<C> connection : connectionManager.getConnections()) {
            if (serviceId.isBoundTo(connection)) {
                for (AdapterProvider<?, C> provider : adapterProviders) {
                    if (provider.isSupported(serviceId)) {
                        ServicePointer<T> pointer = ((AdapterProvider<T, C>) provider).createAdapter(serviceId, connection);
                        return Optional.of(pointer);
                    }
                }
            }
        }
        return Optional.absent();
    }

}
