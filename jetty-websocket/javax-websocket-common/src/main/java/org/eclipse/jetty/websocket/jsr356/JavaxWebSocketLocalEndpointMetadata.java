//
//  ========================================================================
//  Copyright (c) 1995-2017 Mort Bay Consulting Pty. Ltd.
//  ------------------------------------------------------------------------
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  and Apache License v2.0 which accompanies this distribution.
//
//      The Eclipse Public License is available at
//      http://www.eclipse.org/legal/epl-v10.html
//
//      The Apache License v2.0 is available at
//      http://www.opensource.org/licenses/apache2.0.php
//
//  You may elect to redistribute this code under either of these licenses.
//  ========================================================================
//

package org.eclipse.jetty.websocket.jsr356;

import java.lang.invoke.MethodHandle;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.Decoder;
import javax.websocket.Encoder;
import javax.websocket.server.ServerEndpointConfig;

import org.eclipse.jetty.websocket.core.InvalidWebSocketException;

public class JavaxWebSocketLocalEndpointMetadata
{
    private MethodHandle openHandle;
    private MethodHandle closeHandle;
    private MethodHandle errorHandle;

    private MethodHandle textHandle;
    private Class<? extends MessageSink> textSink;
    private MethodHandle binaryHandle;
    private Class<? extends MessageSink> binarySink;

    private MethodHandle pongHandle;

    // EndpointConfig entries
    private Class<? extends Decoder>[] decoders;
    private Class<? extends Encoder>[] encoders;

    // ClientEndpointConfig entries
    private Class<? extends ClientEndpointConfig.Configurator> clientConfigurator;
    private String subProtocols[];

    // ServerEndpointConfig entries
    private Class<? extends ServerEndpointConfig.Configurator> serverConfigurator;

    public void setBinaryHandle(Class<? extends MessageSink> sinkClass, MethodHandle binary, Object origin)
    {
        assertNotSet(this.binaryHandle, "BINARY Handler", origin);
        this.binaryHandle = binary;
        this.binarySink = sinkClass;
    }

    public MethodHandle getBinaryHandle()
    {
        return binaryHandle;
    }

    public Class<? extends MessageSink> getBinarySink()
    {
        return binarySink;
    }

    public void setCloseHandler(MethodHandle close, Object origin)
    {
        assertNotSet(this.closeHandle, "CLOSE Handler", origin);
        this.closeHandle = close;
    }

    public MethodHandle getCloseHandle()
    {
        return closeHandle;
    }

    public void setErrorHandler(MethodHandle error, Object origin)
    {
        assertNotSet(this.errorHandle, "ERROR Handler", origin);
        this.errorHandle = error;
    }

    public MethodHandle getErrorHandle()
    {
        return errorHandle;
    }

    public void setClientConfigurator(Class<? extends ClientEndpointConfig.Configurator> clientConfigurator)
    {
        this.clientConfigurator = clientConfigurator;
    }

    public Class<? extends ClientEndpointConfig.Configurator> getClientConfigurator()
    {
        return clientConfigurator;
    }

    public String[] getSubProtocols()
    {
        return subProtocols;
    }

    public void setSubProtocols(String[] subprotocols)
    {
        this.subProtocols = subprotocols;
    }

    public Class<? extends ServerEndpointConfig.Configurator> getServerConfigurator()
    {
        return serverConfigurator;
    }

    public void setServerConfigurator(Class<? extends ServerEndpointConfig.Configurator> serverConfigurator)
    {
        this.serverConfigurator = serverConfigurator;
    }

    public void setEncoders(Class<? extends Encoder>[] encoders)
    {
        this.encoders = encoders;
    }

    public Class<? extends Encoder>[] getEncoders()
    {
        return encoders;
    }

    public void setDecoders(Class<? extends Decoder>[] decoders)
    {
        this.decoders = decoders;
    }

    public Class<? extends Decoder>[] getDecoders()
    {
        return decoders;
    }

    public void setOpenHandler(MethodHandle open, Object origin)
    {
        assertNotSet(this.openHandle, "OPEN Handler", origin);
        this.openHandle = open;
    }

    public MethodHandle getOpenHandle()
    {
        return openHandle;
    }

    public void setPongHandle(MethodHandle pong, Object origin)
    {
        assertNotSet(this.pongHandle, "PONG Handler", origin);
        this.pongHandle = pong;
    }

    public MethodHandle getPongHandle()
    {
        return pongHandle;
    }

    public void setTextHandler(Class<? extends MessageSink> sinkClass, MethodHandle text, Object origin)
    {
        assertNotSet(this.textHandle, "TEXT Handler", origin);
        this.textHandle = text;
        this.textSink = sinkClass;
    }

    public MethodHandle getTextHandle()
    {
        return textHandle;
    }

    public Class<? extends MessageSink> getTextSink()
    {
        return textSink;
    }

    private void assertNotSet(Object val, String role, Object origin)
    {
        if (val == null)
            return;

        StringBuilder err = new StringBuilder();
        err.append("Cannot replace previously assigned [");
        err.append(role);
        err.append("] at ").append(describeOrigin(val));
        err.append(" with ");
        err.append(describeOrigin(origin));

        throw new InvalidWebSocketException(err.toString());
    }

    private String describeOrigin(Object obj)
    {
        if (obj == null)
        {
            return "<undefined>";
        }

        return obj.toString();
    }
}
