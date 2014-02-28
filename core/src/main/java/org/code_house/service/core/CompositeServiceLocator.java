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
package org.code_house.service.core;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.code_house.service.api.ServiceLocator;
import org.code_house.service.api.ServicePointer;

import com.google.common.base.Optional;

public class CompositeServiceLocator implements ServiceLocator {

    private final List<ServiceLocator> locators;

    public CompositeServiceLocator(List<ServiceLocator> locators) {
        this.locators = locators;
    }

    public <T> Set<ServicePointer<T>> lookup(Class<T> type) {
        Set<ServicePointer<T>> services = new LinkedHashSet<ServicePointer<T>>();

        for (ServiceLocator locator : locators) {
            services.addAll(locator.<T>lookup(type));
        }

        return services;
    }

    public <T> Optional<ServicePointer<T>> lookup(ServicePointer<T> serviceId) {
        for (ServiceLocator locator : locators) {
            Optional<ServicePointer<T>> pointer = locator.<T>lookup(serviceId);
            if (pointer != null) {
                return pointer;
            }
        }

        return Optional.absent();
    }

}
