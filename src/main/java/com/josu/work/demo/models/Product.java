package com.josu.work.demo.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;

@Entity
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class Product {
    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    @XmlElement(name="id")
    private Integer id;

    @XmlElement(name="price")
    private Double price;

    @XmlElement(name="name")
    private String name;

    @XmlElement(name="upc")
    private Double upc;

    @XmlElement(name="discount")
    @ManyToOne(optional = false)
    @JoinColumn(name = "discount_id", nullable = false)
    private Discount discount;
}
