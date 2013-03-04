package com.sumologic.client.collectors.model;

public class Filter {

    public static String EXCLUDE = "Exclude";
    public static String INCLUDE = "Include";
    public static String HASH = "Hash";
    public static String MASK = "Mask";

    private String filterType;
    private String name;
    private String regexp;
    private String mask;

    /**
     * Returns the filter type.
     *
     * @return The filter type.
     */
    public String getFilterType() {
        return filterType;
    }

    /**
     * Sets the filter type.
     */
    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

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
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the regular expression.
     *
     * @return The regular expression.
     */
    public String getRegexp() {
        return regexp;
    }

    /**
     * Sets the regular expression.
     */
    public void setRegexp(String regexp) {
        this.regexp = regexp;
    }

    /**
     * Returns the mask.
     *
     * @return The mask.
     */
    public String getMask() {
        return mask;
    }

    /**
     * Sets the mask.
     */
    public void setMask(String mask) {
        this.mask = mask;
    }
}
