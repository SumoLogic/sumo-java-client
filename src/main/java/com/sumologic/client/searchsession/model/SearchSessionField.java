package com.sumologic.client.searchsession.model;

/**
 * @author Christian Beedgen (christian@sumologic.com)
 */
public class SearchSessionField {

    // Instance fields.

    private String name;
    private String type;
    private boolean isKeyField;

    // Implementation.

    /**
     * Returns the name.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the name.
     *
     * @param name The name.
     * @return The name.
     */
    public SearchSessionField withName(String name) {
        setName(name);
        return this;
    }

    /**
     * Returns the type.
     *
     * @return The type.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type The type.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets the type.
     *
     * @param type The type.
     * @return This object.
     */
    public SearchSessionField withType(String type) {
        setType(type);
        return this;
    }

    /**
     * Returns whether this is a key field.
     *
     * @return Whether this is a key field.
     */
    public boolean isKeyField() {
        return isKeyField;
    }

    /**
     * Sets whether this is a key field.
     *
     * @param isKeyField Whether this is a key field.
     */
    public void setKeyField(boolean isKeyField) {
        this.isKeyField = isKeyField;
    }

    /**
     * Sets whether this is a key field.
     *
     * @param isKeyField Whether this is a key field.
     * @return This object.
     */
    public SearchSessionField withKeyField(boolean isKeyField) {
        setKeyField(isKeyField);
        return this;
    }

    // Object implementation.

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer(128);
        result.append("name: '");
        result.append(name);
        result.append("', type: '");
        result.append(type);
        result.append("', isKeyField: '");
        result.append(isKeyField);
        result.append("'");
        return result.toString();
    }
}