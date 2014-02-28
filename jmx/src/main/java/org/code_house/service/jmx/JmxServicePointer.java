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

import org.code_house.service.api.ServicePointer;
import org.code_house.service.api.WrapperConnection;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import java.util.Map;

public abstract class JmxServicePointer<T> implements ServicePointer<T> {

    private final transient WrapperConnection<MBeanServerConnection> connection;
    private final transient ObjectName objectName;

    private final String connectionId;

    protected JmxServicePointer(WrapperConnection<MBeanServerConnection> connection, ObjectName objectName) {
        this.connection = connection;
        this.connectionId = connection.getConnectionId();
        this.objectName = objectName;
    }

    public boolean isBoundTo(WrapperConnection<?> connection) {
        return connection.getConnection() instanceof MBeanServerConnection && connectionId.equals(connection.getConnectionId());
    }

    protected final <X> X createProxy(Class<X> type) {
        return JMX.newMBeanProxy(connection.getConnection(), objectName, type);
    }

}
