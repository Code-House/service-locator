package org.code_house.service.jolokia;

import org.jolokia.client.J4pClient;
import org.jolokia.client.request.J4pExecRequest;
import org.jolokia.client.request.J4pReadRequest;
import org.jolokia.client.request.J4pResponse;
import org.jolokia.client.request.J4pWriteRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.lang.management.MemoryManagerMXBean;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Jolokia implements InvocationHandler {

    private final J4pClient connection;
    private final ObjectName objectName;

    public Jolokia(J4pClient connection, ObjectName objectName) {
        this.connection = connection;
        this.objectName = objectName;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getParameterTypes().length == 1) {
            connection.execute(new J4pWriteRequest(objectName, method.getName(), args[0]));
        } else if (method.getParameterTypes().length == 0 && method.getReturnType() == Void.class) {
            connection.execute(new J4pExecRequest(objectName, method.getName()));
        } else if (method.getReturnType() != Void.class && method.getParameterTypes().length == 0) {
            J4pResponse<J4pReadRequest> response = connection.execute(new J4pReadRequest(objectName, method.getName().substring(3)));
            return map(method.getReturnType(), response.getValue());
        }
        return null;
    }

    private Object map(Class<?> type, Object value) {
        if (type.isArray()) {
            JSONArray array = (JSONArray) value;
            Object[] javaArray = (Object[]) Array.newInstance(type.getComponentType(), array.size());
            int index = 0;
            for (Object o : array) {
                Array.set(javaArray, index++, map(type.getComponentType(), o));
            }
            return javaArray;
        } else if (String.class.equals(type)) {
            return (String) value;
        }
        return null;
    }

    public static <T> T newMBeanProxy(J4pClient connection, ObjectName objectName, Class<T> interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new Jolokia(connection, objectName));
    }

    public static void main(String[] args) throws MalformedObjectNameException {
        J4pClient client = new J4pClient("http://127.0.0.1:8040/jolokia");

        MemoryManagerMXBean bean = Jolokia.newMBeanProxy(client, new ObjectName("java.lang:type=MemoryManager,name=CodeCacheManager"), MemoryManagerMXBean.class);
        System.out.println(Arrays.toString(bean.getMemoryPoolNames()));
    }

}
