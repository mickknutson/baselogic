package com.baselogic.tutorials;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Constants {

    // Resource bundle name
    public static final String RESOURCE_BUNDLE = "messages";

    // TABLE Constants
    public static final String HEADER_SUD = "sud";


    // TODO HOLD ---->

    // Security roles
    public static final String SECURITY_ROLE = "security-role";
    public static final String SECURITY_ROLE_USER = "user";
    public static final String SECURITY_ROLE_ADMIN = "admin";

    // Date formatting
    public static final String DEFAULT_DATE_FORMAT = "dd-MM-yyyy";
    public static final String DEFAULT_TIME_FORMAT = "dd-MM-yyyy hh:mm:ss";

    // Default diagram image extension, when name cannot be deducted from resource name
    public static final String DEFAULT_DIAGRAM_IMAGE_EXTENSION = "png";

    // MIMETYPES
    public static final String MIMETYPE_BPM = "image/bpm";
    public static final String MIMETYPE_GIF = "image/gif";
    public static final String MIMETYPE_JPEG = "image/jpeg";
    public static final String MIMETYPE_PNG = "image/png";
    public static final Collection<String> DEFAULT_IMAGE_MIMETYPES = Arrays.asList(
            MIMETYPE_BPM, MIMETYPE_GIF, MIMETYPE_JPEG, MIMETYPE_PNG);
    // User Info
    public static final String USER_INFO_BIRTH_DATE = "birthDate";
    public static final String USER_INFO_JOB_TITLE = "jobTitle";
    public static final String USER_INFO_LOCATION = "location";
    public static final String USER_INFO_PHONE = "phone";
    public static final String USER_INFO_TWITTER = "twitterName";
    public static final String USER_INFO_SKYPE = "skype";
    public static final String EMAIL_RECIPIENT = "recipients";
    public static final String EMAIL_SENT_DATE = "sentDate";
    public static final String EMAIL_RECEIVED_DATE = "receivedDate";
    public static final String EMAIL_SUBJECT = "subject";
    public static final String EMAIL_HTML_CONTENT = "htmlContent";
    public static Map<String, String> MIMETYPE_EXTENSION_MAPPING;

    static {
        Map<String, String> mapping = new HashMap<String, String>();
        mapping.put(MIMETYPE_BPM, "bpm");
        mapping.put(MIMETYPE_GIF, "gif");
        mapping.put(MIMETYPE_JPEG, "jpg");
        mapping.put(MIMETYPE_PNG, "png");
        MIMETYPE_EXTENSION_MAPPING = Collections.unmodifiableMap(mapping);
    }


}
