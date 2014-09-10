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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.modeshape.jcr.JcrSession;

/**
 * <p>InjectableSessionTest class.</p>
 *
 * @author awoods
 */
// TODO
@Ignore("Haven't yet mocked out the machinery behind session-creation.")
public class InjectableSessionTest {

    InjectableSession testObj;

    @Mock
    private JcrSession mockSession;

    @Mock
    private SessionFactory mockSessionFactory;

    @Before
    public void setUp() {
        initMocks(this);
        final HttpServletRequest mockHttpServletRequest = mock(HttpServletRequest.class);
        when(mockSessionFactory.getSession(mockHttpServletRequest)).thenReturn(mockSession);
        testObj = new InjectableSession(mockSessionFactory, mockHttpServletRequest);
    }

    @Test
    public void testSomething() {
    }
}