package com.baselogic.tutorials.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Object Type
 */
@Embeddable
public class ObjectTypeEntity implements Serializable {

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    protected Calendar timestamp;
    @Column
    String creator;
    String text;
    @Enumerated(EnumType.STRING)
    private com.baselogic.tutorials.domain.enums.ObjectType type;

    public ObjectTypeEntity() {
    }

    public ObjectTypeEntity(String creator, String text, Calendar timestamp) {
        this.creator = creator;
        this.text = text;
        this.timestamp = timestamp;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Calendar getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Calendar timestamp) {
        this.timestamp = timestamp;
    }
}
