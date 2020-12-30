package com.Server.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity reservations to store Reservation data.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rent")
    /**idrent*/
    private Long idrent;


    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_car", referencedColumnName = "id_car")
    /**car*/
    private Car car;

    @Temporal(TemporalType.TIMESTAMP)
    @Type(type = "date")
    @Column(name = "data_from")
    /**dataFrom*/
    private Date dataFrom;

    @Temporal(TemporalType.TIMESTAMP)
    @Type(type = "date")
    @Column(name = "data_to")
    /**dataTo*/
    private Date dataTo;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "localization_start", referencedColumnName = "id_localization")
    /**localizationStart*/
    private Localization localizationStart;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "localization_end", referencedColumnName = "id_localization")
    /**localizationEnd*/
    private Localization localizationEnd;

    @Column(name = "price")
    /**price*/
    private float price;

    /**Constructor*/
    public Reservation() {

    }

    /**Constructor*/
    public Reservation(Car car, User user, Date dataFrom, Date dataTo, Localization localizationStart, Localization localizationEnd, float price) {
        this.car = car;
        this.dataFrom = dataFrom;
        this.dataTo = dataTo;
        this.localizationStart = localizationStart;
        this.localizationEnd = localizationEnd;
        this.price = price;
    }

    /**
     *
     * @return Idrent
     */
    public Long getIdrent() {
        return idrent;
    }

    /**
     *
     * @param idrent Idrent
     */
    public void setIdrent(Long idrent) {
        this.idrent = idrent;
    }

    /**
     *
     * @return Car
     */
    public Car getCar() {
        return car;
    }

    /**
     *
     * @param car setCar
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     *
     * @return DataFrom
     */
    public Date getDataFrom() {
        return dataFrom;
    }

    /**
     *
     * @param dataFrom setDataFrom
     */
    public void setDataFrom(Date dataFrom) {
        this.dataFrom = dataFrom;
    }

    /**
     *
     * @return DataTo
     */
    public Date getDataTo() {
        return dataTo;
    }

    /**
     *
     * @param dataTo setDataTo
     */
    public void setDataTo(Date dataTo) {
        this.dataTo = dataTo;
    }

    /**
     *
     * @return LocalizationStart
     */
    public Localization getLocalizationStart() {
        return localizationStart;
    }

    /**
     *
     * @param localizationStart setLocalizationStart
     */
    public void setLocalizationStart(Localization localizationStart) {
        this.localizationStart = localizationStart;
    }

    /**
     *
     * @return LocalizationEnd
     */
    public Localization getLocalizationEnd() {
        return localizationEnd;
    }

    /**
     *
     * @param localizationEnd setLocalizationEnd
     */
    public void setLocalizationEnd(Localization localizationEnd) {
        this.localizationEnd = localizationEnd;
    }

    /**
     *
     * @return Price
     */
    public float getPrice() {
        return price;
    }

    /**
     *
     * @param price setPrice
     */
    public void setPrice(float price) {
        this.price = price;
    }
}
