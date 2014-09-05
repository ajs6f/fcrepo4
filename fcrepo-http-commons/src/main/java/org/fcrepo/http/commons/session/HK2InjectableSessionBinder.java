/**
 * 
 */
package org.fcrepo.http.commons.session;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;


/**
 * @author ajs6f
 *
 */
public class HK2InjectableSessionBinder extends AbstractBinder {

    /* (non-Javadoc)
     * @see org.glassfish.hk2.utilities.binding.AbstractBinder#configure()
     */
    @Override
    protected void configure() {
        bind(InjectableSession.class).to(InjectableSession.class).in(RequestScoped.class);
    }

}
