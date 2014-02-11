package org.code_house.service.jmx;

import com.google.common.collect.ImmutableSet;
import org.code_house.service.core.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MBeanServerConnection;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.remote.JMXConnectionNotification;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class JmxConnectionManager implements ConnectionManager<MBeanServerConnection>, NotificationListener {

    private final Logger logger = LoggerFactory.getLogger(JmxConnectionManager.class);
    private final Set<MBeanServerConnection> connections = new CopyOnWriteArraySet<MBeanServerConnection>();

    @Override
    public Set<MBeanServerConnection> getConnections() {
        return ImmutableSet.<MBeanServerConnection>builder()
            .addAll(connections)
            .build();
    }

    public void addConnection(String uri, Map<String, ?> parameters) {
        try {
            JMXServiceURL serviceURL = new JMXServiceURL(uri);
            JMXConnector connector = JMXConnectorFactory.newJMXConnector(serviceURL, parameters);
            connector.addConnectionNotificationListener(this, null, serviceURL);
            connector.connect();
            connections.add(connector.getMBeanServerConnection());
        } catch (MalformedURLException e) {
            logger.error("Wrong service url {}", uri, e);
        } catch (IOException e) {
            logger.error("Failed to make connection to {}", uri, e);
        }
    }

    @Override
    public void handleNotification(Notification notification, Object handback) {
        logger.warn("Change in connection state {} {}", handback, notification.getType());
    }
}
