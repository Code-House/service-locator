package org.code_house.service.core;

import org.code_house.service.api.ServicePointer;

import java.io.Serializable;
import java.util.Set;

/**
 * Generalization which allows to detach logic necessary for creating adapters.
 *
 * @param <T> Type of adapter.
 */
public interface AdapterProvider<T, C> {

    boolean isSupported(Class<?> type);

    <P extends Serializable> Set<ServicePointer<T, P>> createAdapters(C connection);

}
