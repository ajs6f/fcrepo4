/**
 * Copyright 2014 DuraSpace, Inc.
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
package org.fcrepo.http.commons.session;

import javax.ws.rs.ext.Provider;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;

/**
 * @author ajs6f
 *
 */
@Provider
public class HK2InjectableSessionBinder extends AbstractBinder {

    /* (non-Javadoc)
     * @see org.glassfish.hk2.utilities.binding.AbstractBinder#configure()
     */
    @Override
    protected void configure() {
        bind(InjectableSession.class).to(InjectableSession.class).in(RequestScoped.class);
    }

}
