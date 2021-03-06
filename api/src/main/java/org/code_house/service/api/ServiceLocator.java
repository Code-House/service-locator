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

import java.util.Set;

import com.google.common.base.Optional;

/**
 * Service locator used to lookup specified services.
 */
public interface ServiceLocator {

    /**
     * Lookup services based on the type.
     *
     * @param type Type of service.
     * @param <T> Instance type.
     * @return All services supporting
     */
    <T> Set<ServicePointer<T>> lookup(Class<T> type);

    <T> Optional<ServicePointer<T>> lookup(ServicePointer<T> serviceId);

}
