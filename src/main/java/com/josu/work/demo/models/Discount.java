package com.josu.work.demo.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.*;

@Entity
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Discount {

    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    @XmlElement(name="id")
    private Integer id;

    @XmlElement(name="value")
    private Double value;

}
