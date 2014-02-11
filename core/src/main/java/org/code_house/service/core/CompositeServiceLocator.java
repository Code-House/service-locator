package org.code_house.service.core;

import org.code_house.service.api.ServiceLocator;
import org.code_house.service.api.ServicePointer;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class CompositeServiceLocator implements ServiceLocator {

    private final List<ServiceLocator> locators;

    public CompositeServiceLocator(List<ServiceLocator> locators) {
        this.locators = locators;
    }

    public <T, P extends Serializable> Set<ServicePointer<T, P>> lookup(Class<T> type) {
        Set<ServicePointer<T, P>> services = new LinkedHashSet<ServicePointer<T, P>>();

        for (ServiceLocator locator : locators) {
            services.addAll(locator.<T, P>lookup(type));
        }

        return services;
    }

}
