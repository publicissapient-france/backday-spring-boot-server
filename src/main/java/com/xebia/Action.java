package com.xebia;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.data.annotation.Id;

@XmlRootElement
public class Action {

    @Id
    @XmlElement
    private String id;

    @XmlElement
    private Long value;

    public Action(final String id, final Long value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Action{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public Long getValue() {
        return value;
    }
}
