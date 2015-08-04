package com.baselogic.tutorials.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Malfunction
 */
@Embeddable
public class Malfunction extends AbstractValueObject{

    @Column
    private String name;
    private String description;


}
