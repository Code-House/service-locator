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
package org.code_house.service.jolokia;

import org.code_house.service.core.AbstractServiceLocator;
import org.code_house.service.core.AdapterProvider;
import org.code_house.service.core.ConnectionManager;
import org.jolokia.client.J4pClient;

import javax.management.MBeanServer;
import java.io.Serializable;
import java.util.List;

public class JolokiaServiceLocator extends AbstractServiceLocator<J4pClient> {
    public JolokiaServiceLocator(ConnectionManager<J4pClient> connectionManager, List<AdapterProvider<?, J4pClient>> adapterProviders) {
        super(connectionManager, adapterProviders);
    }
}
