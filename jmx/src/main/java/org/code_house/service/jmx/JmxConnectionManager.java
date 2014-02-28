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

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.code_house.service.api.WrapperConnection;
import org.code_house.service.core.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableSet;

public class JmxConnectionManager implements ConnectionManager<MBeanServerConnection> {

    private final Logger logger = LoggerFactory.getLogger(JmxConnectionManager.class);
    private final Set<WrapperConnection<MBeanServerConnection>> connections = new CopyOnWriteArraySet<WrapperConnection<MBeanServerConnection>>();
    private final AtomicLong identifier = new AtomicLong();

    public Set<WrapperConnection<MBeanServerConnection>> getConnections() {
        return ImmutableSet.<WrapperConnection<MBeanServerConnection>>builder()
            .addAll(connections)
            .build();
    }

    public void addConnection(String uri, Map<String, ?> parameters) {
        try {
            JMXServiceURL serviceURL = new JMXServiceURL(uri);
            JMXConnector connector = JMXConnectorFactory.newJMXConnector(serviceURL, parameters);
            connector.connect();
            connections.add(new WrapperConnection<MBeanServerConnection>(new Long(identifier.incrementAndGet()).toString(), connector.getMBeanServerConnection()));
        } catch (MalformedURLException e) {
            logger.error("Wrong service url {}", uri, e);
        } catch (IOException e) {
            logger.error("Failed to make connection to {}", uri, e);
        }
    }


}
