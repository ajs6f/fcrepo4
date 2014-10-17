package org.fcrepo.http.commons.exceptionhandlers;

import org.glassfish.jersey.spi.ExtendedExceptionMapper;


/**
 * An {@link ExtendedExceptionMapper} that may or may not emit stacktraces into HTTP responses, based on
 * configuration.
 *
 * @author ajs6f
 *
 * @param <E>
 * @see ExtendedExceptionMapper
 */
public abstract class StackTraceEmittingExceptionMapper<E extends Throwable> implements ExtendedExceptionMapper<E> {

    protected Boolean showStackTrace = true;

    /**
     * Set whether the full stack trace should be returned as part of the
     * error response. This may be a bad idea if the stack trace is exposed
     * to the public.
     * @param showStackTrace
     */
    public void setShowStackTrace(final Boolean showStackTrace) {
        this.showStackTrace = showStackTrace;
    }
}
