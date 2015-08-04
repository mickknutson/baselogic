package com.baselogic.tutorials.domain;


import com.baselogic.bfl.Constants;

/**
 * Constants for JPA related properties
 */
public final class JpaConstants extends Constants {

    //-----------------------------------------------------------------------//
    // Attributes
    //-----------------------------------------------------------------------//
    public static final String PERSISTENCEUNIT = "com.baselogic.bfl";

    public static final String PERSISTENCEUNIT_DERBY = "DERBY_PU";
    public static final String FINDALLFINDERNAME = "findAll";

    public static final String FINDALLQUERY = "SELECT e FROM SUD_REQUEST e";

    //-----------------------------------------------------------------------//
    // Table / Column Names
    //-----------------------------------------------------------------------//
    public static final String AUDIT_USER = "AUDIT_USER";
    public static final String AUDIT_TIMESTAMP = "AUDIT_TIMESTAMP";

    public static final String INCIDENT_ID = "INCIDENT_ID";

    public static final String INCIDENT_COMMENTS = "INCIDENT_COMMENTS";
    public static final String COMMENT = "COMMENT";




    public static final String PERSON = "PERSON";
    public static final String CUSTOMER_ADDRESSES = "CUST_ADDRESSES";
    public static final String ADDRESS_KEY = "ADDRESS_KEY";
    public static final String HOBBIES = "HOBBIES";
    public static final String HOBBY_NAME = "HOBBY_NAME";

    public static final String PHONE_NUMBER = "PHONE_NUMBER";
    public static final String AUDIT_ENTRY = "auditEntry";
    public static final String AUDIT_ENTRY_ID = "AUDIT_ENTRY_ID";
    public static final String AUDIT_FIELDS = "AUDIT_FIELDS";

    public static final String CONTACT_ENTRY = "customerEntry";

}
