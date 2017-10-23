package com.sumologic.client.collectors.model;

public class StreamingMetricsSource extends Source {

    private static String PROTOCOL = "protocol";
    private static String PORT = "port";

    public StreamingMetricsSource() { setSourceType(SourceType.STREAMING_METRICS.getType()); }

    /**
     * Returns the protocol.
     *
     * @return The protocol.
     */
    public String getProtocol() {
        return getProperty(PROTOCOL);
    }

    /**
     * Sets the protocol.
     */
    public void setProtocol(String protocol) {
        setProperty(PROTOCOL, protocol);
    }

    /**
     * Returns the port.
     *
     * @return The port.
     */
    public Integer getPort() {
        return getProperty(PORT);
    }

    /**
     * Sets the port.
     */
    public void setPort(Integer port) {
        setProperty(PORT, port);
    }
}
