package com.sumologic.client.collectors.model;

public class HttpSource extends Source {

    public static String ENCODING = "encoding";
    public static String ENDPOINT = "endPoint";
    public static String MESSAGE_PER_REQUEST = "messagePerRequest";

    public HttpSource() {
        setSourceType(SourceType.HTTP.getType());
    }

    /**
     * Returns the encoding.
     *
     * @return The encoding.
     */
    public String getEncoding() {
        return getProperty(ENCODING);
    }

    /**
     * Sets the encoding.
     */
    public void setEncoding(String encoding) {
        setProperty(ENCODING, encoding);
    }

    /**
     * Returns the URL endpoint.
     *
     * @return The URL endpoint.
     */
    public String getEndpoint() {
        return getProperty(ENDPOINT);
    }

    /**
     * Returns whether each request is a single message.
     *
     * @return Whether each request is a single message.
     */
    public Boolean isMessagePerRequest() {
        return getProperty(MESSAGE_PER_REQUEST);
    }

    /**
     * Sets whether each request is a single message.
     */
    public void setMessagePerRequest(Boolean messagePerRequest) {
        setProperty(MESSAGE_PER_REQUEST, messagePerRequest);
    }
}
