package com.Server.dto.Request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Class DTO use to add car.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 2.0.
 * @since 2020-04-27.
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
public class CarRequest implements Serializable {
    @NotBlank
    /**Mark car*/
    private String mark;
    @NotBlank
    /**Model car*/
    private String model;
    @NotBlank
    /**Type car*/
    private String type;
    @NotNull
    @Min(1980)
    /**Year car*/
    private int yearProduction;
    @NotBlank
    /**Color car*/
    private String color;
    @NotNull
    @Min(500)
    /**Engine car*/
    private int engine;
    @NotBlank
    /**City car*/
    private String city;
    @NotNull
    @Min(40)
    /**Money car*/
    private float money;
    @NotBlank
    /**Image car*/
    private String image;

}
