package com.Server.mapper.impl;

import com.Server.dto.Request.LocalizationRequest;
import com.Server.dto.Response.LocalizationResponse;
import com.Server.mapper.Mapper;
import com.Server.entiy.Localization;
import org.springframework.stereotype.Component;

/**
 * Class Mapper use to mapping Localization.
 *
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-04-27
 */

@Component
public class LocalizationMapper implements Mapper<Localization, LocalizationResponse, LocalizationRequest> {

    /**
     * @param localization param mapped to dto
     * @return dto object
     */
    @Override
    public LocalizationResponse toDto(final Localization localization) {
        return new LocalizationResponse().builder()
                .id(localization.getId())
                .city(localization.getCity())
                .build();
    }

    /**
     * @param localizationRequest param mapped to entity
     * @return entity object
     */
    @Override
    public Localization toEntity(final LocalizationRequest localizationRequest) {
        return new Localization().builder()
                .city(localizationRequest.getCity())
                .build();
    }

    /**
     * @param localization        old param
     * @param localizationRequest new param
     * @return new localization object.
     */
    @Override
    public Localization update(Localization localization, final LocalizationRequest localizationRequest) {
        localization.setCity(localization.getCity());
        return localization;
    }
}
