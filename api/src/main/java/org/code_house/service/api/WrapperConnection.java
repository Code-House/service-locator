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
package org.code_house.service.api;

/**
 * Wrapper for managed connections.
 * 
 * @author lukasz
 *
 * @param <C> Type of connection.
 */
public final class WrapperConnection<C> {

    private final String connectionId;
    private final C connection;

    public WrapperConnection(String connectionId, C connection) {
        this.connectionId = connectionId;
        this.connection = connection;
    }

    public String getConnectionId() {
        return connectionId;
    }

    public C getConnection() {
        return connection;
    }

}
