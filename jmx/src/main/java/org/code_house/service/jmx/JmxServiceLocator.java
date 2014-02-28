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
package org.code_house.service.jmx;

import org.code_house.service.core.AbstractServiceLocator;
import org.code_house.service.core.AdapterProvider;
import org.code_house.service.core.ConnectionManager;

import javax.management.MBeanServer;
import javax.management.MBeanServerConnection;
import java.io.Serializable;
import java.util.List;

public class JmxServiceLocator extends AbstractServiceLocator<MBeanServerConnection> {
    public JmxServiceLocator(ConnectionManager<MBeanServerConnection> connectionManager, List<AdapterProvider<?, MBeanServerConnection>> adapterProviders) {
        super(connectionManager, adapterProviders);
    }
}
