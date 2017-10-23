package com.sumologic.client.collectors.model;

public class StreamingMetricsSource extends Source {

    private static String PROTOCOL = "protocol";
    private static String PORT = "port";
    private static String CONTENT_TYPE = "content_type";

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

    /**
     * Returns the content type.
     *
     * @return The content type.
     */
    public String getContentType() {
        return getProperty(CONTENT_TYPE);
    }

    /**
     * Sets the protocol.
     */
    public void setContentType(String contentType) {
        setProperty(CONTENT_TYPE, contentType);
    }
}
