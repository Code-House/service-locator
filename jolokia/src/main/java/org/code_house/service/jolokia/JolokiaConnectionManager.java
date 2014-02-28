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

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;

import org.code_house.service.api.WrapperConnection;
import org.code_house.service.core.ConnectionManager;
import org.jolokia.client.J4pClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableSet;

public class JolokiaConnectionManager implements ConnectionManager<J4pClient> {

    private final Logger logger = LoggerFactory.getLogger(JolokiaConnectionManager.class);
    private final Set<WrapperConnection<J4pClient>> connections = new CopyOnWriteArraySet<WrapperConnection<J4pClient>>();
    private final AtomicLong connectionCounter = new AtomicLong();

    public Set<WrapperConnection<J4pClient>> getConnections() {
        return ImmutableSet.<WrapperConnection<J4pClient>>builder()
            .addAll(connections)
            .build();
    }

    public void addConnection(String uri, Map<String, ?> parameters) {
        connections.add(new WrapperConnection<J4pClient>(new Long(connectionCounter.incrementAndGet()).toString(), new J4pClient(uri)));
    }

}
