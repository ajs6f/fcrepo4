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
package org.fcrepo.http.commons.exceptionhandlers;

import static com.google.common.base.Throwables.getStackTraceAsString;
import static javax.ws.rs.core.Response.serverError;
import static org.slf4j.LoggerFactory.getLogger;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.fcrepo.kernel.exception.RepositoryRuntimeException;
import org.glassfish.hk2.api.MultiException;
import org.slf4j.Logger;


/**
 * Handles Jersey 2's weird and unpleasant {@link MultiException}.
 *
 * @author ajs6f
 *
 */
@Provider
public class MultiExceptionMapper extends StackTraceEmittingExceptionMapper<MultiException> {

    private static final Logger log = getLogger(MultiExceptionMapper.class);

    @Override
    public boolean isMappable(MultiException e) {
        log.trace("Received MultiException {}", e);
        final Throwable underlyingException = e.getCause();
        if(underlyingException instanceof RepositoryRuntimeException) {
            log.trace("Rethrowing RepositoryRuntimeException {}", underlyingException);
            throw (RepositoryRuntimeException) underlyingException;
        }
        return false;
    }

    @Override
    public Response toResponse(final MultiException e) {
        return serverError().entity(showStackTrace ? getStackTraceAsString(e) : null).build();
    }
}
