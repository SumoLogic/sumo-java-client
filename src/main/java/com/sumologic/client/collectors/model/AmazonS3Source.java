package com.sumologic.client.collectors.model;

import com.sumologic.client.util.JacksonUtils;

public class AmazonS3Source extends Source {

    private static String KEY_ID = "keyId";
    private static String SECRET_KEY = "secretKey";
    private static String BUCKET_NAME = "bucketName";
    private static String PATH_EXPRESSION = "pathExpression";
    private static String SCAN_INTERVAL = "scanInterval";

    public AmazonS3Source() {
        setSourceType(SourceType.AMAZON_S3.getType());
    }

    /**
     * Returns the key id (hidden in server response).
     *
     * @return The key id.
     */
    public String getKeyId() {
        return getProperty(KEY_ID);
    }

    /**
     * Sets the key id.
     */
    public void setKeyId(String keyId) {
        setProperty(KEY_ID, keyId);
    }

    /**
     * Returns the secret key (hidden in server response).
     *
     * @return The secret key.
     */
    public String getSecretKey() {
        return getProperty(SECRET_KEY);
    }

    /**
     * Sets the secret key.
     */
    public void setSecretKey(String secretKey) {
        setProperty(SECRET_KEY, secretKey);
    }

    /**
     * Returns the bucket name.
     *
     * @return The bucket name.
     */
    public String getBucketName() {
        return getProperty(BUCKET_NAME);
    }

    /**
     * Sets the bucket name.
     */
    public void setBucketName(String bucketName) {
        setProperty(BUCKET_NAME, bucketName);
    }

    /**
     * Returns the path expression.
     *
     * @return The path expression.
     */
    public String getPathExpression() {
        return getProperty(PATH_EXPRESSION);
    }

    /**
     * Sets the path expression.
     */
    public void setPathExpression(String pathExpression) {
        setProperty(PATH_EXPRESSION, pathExpression);
    }

    /**
     * Returns the scan interval.
     *
     * @return The scan interval.
     */
    public Long getScanInterval() {
        return JacksonUtils.asLong(getProperty(SCAN_INTERVAL));
    }

    /**
     * Sets the scan interval.
     */
    public void setScanInterval(Long scanInterval) {
        setProperty(SCAN_INTERVAL, scanInterval);
    }
}
