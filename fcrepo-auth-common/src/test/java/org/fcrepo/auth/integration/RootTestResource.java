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
package org.fcrepo.auth.integration;

import org.fcrepo.http.commons.AbstractResource;
import org.fcrepo.http.commons.session.InjectedSession;
import org.modeshape.jcr.api.JcrTools;
import org.slf4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * This class acts as the REST Resource endpoint against which integration tests are executed.
 * This is used instead of the real F4 REST API for two reasons:
 * - These integration tests are intended to test the AuthZ functionality, not the F4 REST API
 * - A circular dependency between fcrepo-auth-common <--> fcrepo-http-api is bad
 *
 * @author awoods
 * @since 2014-06-26
 */
@Component
@Scope("prototype")
@Path("/{path: .*}")
public class RootTestResource extends AbstractResource {

    @InjectedSession
    protected Session session;

    private static final Logger LOGGER = getLogger(RootTestResource.class);

    @PUT
    public Response put(@PathParam("path") final List<PathSegment> pathList) throws Exception {
        final String path = toPath(pathList);
        LOGGER.trace("PUT: {}", path);
        return doRequest(path);
    }

    @POST
    public Response post(@PathParam("path") final List<PathSegment> pathList) throws Exception {
        final String path = toPath(pathList);
        LOGGER.trace("POST: {}", path);
        return doRequest(path);
    }

    private Response doRequest(final String path) throws RepositoryException {
        final JcrTools jcrTools = new JcrTools();
        final Node node = jcrTools.findOrCreateNode(session, path);
        final URI location = uriInfo.getBaseUriBuilder().path(node.getPath()).build();
        return Response.created(location).build();
    }

}