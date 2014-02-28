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

import java.util.Set;

import org.code_house.service.api.ServicePointer;
import org.code_house.service.api.WrapperConnection;

/**
 * Generalization which allows to detach logic necessary for creating adapters.
 *
 * @param <T> Type of adapter.
 * @param <C> Connection type.
 */
public interface AdapterProvider<T, C> {

    boolean isSupported(Class<?> type);

    boolean isSupported(ServicePointer<?> serviceId);

    Set<ServicePointer<T>> createAdapters(WrapperConnection<C> connection);

    ServicePointer<T> createAdapter(ServicePointer<T> identifier, WrapperConnection<C> connection);

}
