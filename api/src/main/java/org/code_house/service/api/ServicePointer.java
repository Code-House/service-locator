package org.code_house.service.api;

import java.io.Serializable;

/**
 * Very simple pointer to service lookup results. Implementations must
 * take care about serialization.
 * 
 * @param <T> Type of the service.
 */
public interface ServicePointer<T, P extends Serializable> extends Serializable {

    T getService();

    P getServiceId();

    ServiceLocator getServiceLocator();

}
