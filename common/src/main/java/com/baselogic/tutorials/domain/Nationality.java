package com.baselogic.tutorials.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Nationality
 */
@Embeddable
public class Nationality extends AbstractValueObject {

    @Column
    String name;
    String description;

}
