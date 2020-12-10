package com.Server.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rent")
    private Long idrent;


    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_car", referencedColumnName = "id_car")
    private Car car;

    @Temporal(TemporalType.TIMESTAMP)
    @Type(type = "date")
    @Column(name = "data_from")
    private Date dataFrom;

    @Temporal(TemporalType.TIMESTAMP)
    @Type(type = "date")
    @Column(name = "data_to")
    private Date dataTo;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "localization_start", referencedColumnName = "id_localization")
    private Localization localizationStart;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "localization_end", referencedColumnName = "id_localization")
    private Localization localizationEnd;

    @Column(name = "price")
    private float price;

    public Reservation() {

    }

    public Reservation(Car car, User user, Date dataFrom, Date dataTo, Localization localizationStart, Localization localizationEnd, float price) {
        this.car = car;
        this.dataFrom = dataFrom;
        this.dataTo = dataTo;
        this.localizationStart = localizationStart;
        this.localizationEnd = localizationEnd;
        this.price = price;
    }

    public Long getIdrent() {
        return idrent;
    }

    public void setIdrent(Long idrent) {
        this.idrent = idrent;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getDataFrom() {
        return dataFrom;
    }

    public void setDataFrom(Date dataFrom) {
        this.dataFrom = dataFrom;
    }

    public Date getDataTo() {
        return dataTo;
    }

    public void setDataTo(Date dataTo) {
        this.dataTo = dataTo;
    }

    public Localization getLocalizationStart() {
        return localizationStart;
    }

    public void setLocalizationStart(Localization localizationStart) {
        this.localizationStart = localizationStart;
    }

    public Localization getLocalizationEnd() {
        return localizationEnd;
    }

    public void setLocalizationEnd(Localization localizationEnd) {
        this.localizationEnd = localizationEnd;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
