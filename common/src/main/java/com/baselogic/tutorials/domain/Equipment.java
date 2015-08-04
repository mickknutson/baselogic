package com.baselogic.tutorials.domain;

import com.baselogic.tutorials.domain.parachute.Parachute;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Equipment Used
 */
@Embeddable
public class Equipment extends AbstractEntity
        implements Serializable {

    @Column
    String suit;
    String container;
    Parachute parachute;
    String deploymentMethod;

}

