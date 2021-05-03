package com.Server.entiy;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity reservations to store Reservation data.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 2.0.
 * @since 2020-04-27.
 */
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
@Builder
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rent")
    /**idrent*/
    private Long idrent;

    @ManyToOne
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

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "price")
    /**price*/
    private float price;


    /** Constructor Class Reservation
     *
     * @param car Car Class Object
     * @param user User Class Object
     * @param dataFrom Date of the reservation start
     * @param dataTo Date of the reservation end
     * @param localizationStart The city where the reservations started
     * @param localizationEnd The city where the reservations ended
     * @param price  Reservation cost
     */
    public Reservation(Car car, User user, Date dataFrom, Date dataTo, Localization localizationStart, Localization localizationEnd, float price) {
        this.car = car;
        this.user = user;
        this.dataFrom = dataFrom;
        this.dataTo = dataTo;
        this.localizationStart = localizationStart;
        this.localizationEnd = localizationEnd;
        this.price = price;
    }

}
