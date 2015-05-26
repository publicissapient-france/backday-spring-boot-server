package com.xebia;

import org.springframework.data.annotation.Id;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Action {

    @Id
    @XmlElement
    private String id;

    @XmlElement
    private Integer value;

    public Action(final String id, final Integer value) {
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

    public Integer getValue() {
        return value;
    }
}
