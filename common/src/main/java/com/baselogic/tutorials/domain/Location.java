package com.baselogic.tutorials.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Location
 */
@Embeddable
public class Location extends AbstractValueObject {

    @Column
    private String name;
    private String alternateName;
    private String city;
    private String state;
    private String country;
    private String description;
    private String notes;


    //--- Setter/Getter methods ---------------------------------------------//


}
