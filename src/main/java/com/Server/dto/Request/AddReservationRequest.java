package com.Server.dto.Request;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Class DTO use to add reservation.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

public class AddReservationRequest {
    @NotNull
    @Min(1)
    /**id user*/
    private Long id_user;
    @NotNull
    @Min(1)
    /**id car*/
    private int id_car;
    /** dato to reservation*/
    private String dateto;
    /** data from reservation*/
    private String datefrom;
    @NotBlank
    /**localization end*/
    private String localization_end;
    @NotBlank
    /**localization start*/
    private String localization_start;

    /**Constructor*/
    public AddReservationRequest() {
    }

    /**Constructor*/
    public AddReservationRequest(Long id_user, int id_car, String dateto, String datefrom, String localization_end, String localization_start) {
        this.id_user = id_user;
        this.id_car = id_car;
        this.dateto = dateto;
        this.datefrom = datefrom;
        this.localization_end = localization_end;
        this.localization_start = localization_start;
    }

    /**
     *
     * @return Iduser
     */
    public Long getId_user() {
        return id_user;
    }

    /**
     *
     * @return Idcar
     */
    public int getId_car() {
        return id_car;
    }

    /**
     * @return Dateto
     */
    public String getDateto() {
        return dateto;
    }

    /**
     *
     * @return Datefrom
     */
    public String getDatefrom() {
        return datefrom;
    }

    /**
     *
     * @return Localizationend
     */
    public String getLocalization_end() {
        return localization_end;
    }

    /**
     *
     * @return Localizationstart
     */
    public String getLocalization_start() {
        return localization_start;
    }

    /**
     *
     * @param id_user setIduser
     */
    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }


    /**
     * @param id_car setIdcar
     */
    public void setId_car(int id_car) {
        this.id_car = id_car;
    }

    /**
     *
     * @param dateto setDateto
     */
    public void setDateto(String dateto) {
        this.dateto = dateto;
    }

    /**
     * @param datefrom setDatefrom
     */
    public void setDatefrom(String datefrom) {
        this.datefrom = datefrom;
    }

    /**
     *
     * @param localization_end setLocalizationend
     */
    public void setLocalization_end(String localization_end) {
        this.localization_end = localization_end;
    }

    /**
     *
     * @param localization_start setLocalizationstart
     */
    public void setLocalization_start(String localization_start) {
        this.localization_start = localization_start;
    }
}
