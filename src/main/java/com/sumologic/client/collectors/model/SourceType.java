package com.sumologic.client.collectors.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumeration of available source types (not necessarily comprehensive).
 */
public enum SourceType {
    LOCAL_FILE("LocalFile", LocalFileSource.class),
    REMOTE_FILE("RemoteFile", RemoteFileSource.class),
    SYSLOG("Syslog", SyslogSource.class),
    LOCAL_WINDOWS_EVENT_LOG("LocalWindowsEventLog", WindowsEventLogSource.class),
    REMOTE_WINDOWS_EVENT_LOG("RemoteWindowsEventLog", RemoteWindowsEventLogSource.class),
    SCRIPT("Script", ScriptSource.class),
    ALERT("Alert", AlertSource.class),
    AMAZON_S3("AmazonS3", AmazonS3Source.class),
    HTTP("HTTP", HttpSource.class);

    private String type;
    private Class<? extends Source> sourceClass;

    private SourceType(String type, Class<? extends Source> sourceClass) {
        this.type = type;
        this.sourceClass = sourceClass;
    }

    public String getType() {
        return type;
    }

    public Class<? extends Source> getSourceClass() {
        return sourceClass;
    }

    // Static mapping

    private static Map<String, Class<? extends Source>> SOURCE_TYPE_MAP =
            new HashMap<String, Class<? extends Source>>();

    static {
        for (SourceType sourceType : values()) {
            SOURCE_TYPE_MAP.put(sourceType.getType(), sourceType.getSourceClass());
        }
    }

    public static Class<? extends Source> getSourceClass(String type) {
        return SOURCE_TYPE_MAP.get(type);
    }
}
