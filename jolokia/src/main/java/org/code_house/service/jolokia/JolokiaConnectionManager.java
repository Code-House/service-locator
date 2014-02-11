package org.code_house.service.jolokia;

import com.google.common.collect.ImmutableSet;
import org.code_house.service.core.ConnectionManager;
import org.jolokia.client.J4pClient;
import org.jolokia.client.J4pClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MBeanServerConnection;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class JolokiaConnectionManager implements ConnectionManager<J4pClient> {

    private final Logger logger = LoggerFactory.getLogger(JolokiaConnectionManager.class);
    private final Set<J4pClient> connections = new CopyOnWriteArraySet<J4pClient>();

    @Override
    public Set<J4pClient> getConnections() {
        return ImmutableSet.<J4pClient>builder()
            .addAll(connections)
            .build();
    }

    public void addConnection(String uri, Map<String, ?> parameters) {
        connections.add(new J4pClient(uri));
    }

}
