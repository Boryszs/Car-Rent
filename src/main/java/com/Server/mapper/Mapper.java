package com.Server.mapper;

import org.springframework.web.bind.annotation.Mapping;

/**
 * Interface generic use for mapper class.
 *
 * @param <U> param u
 * @param <V> param v
 * @param <Z> param z
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-04-27
 */
public interface Mapper<U, V, Z> {
    /**
     * @param u param u
     * @return param z
     */
    public V toDto(final U u);

    /**
     * @param z param z
     * @return param u
     */
    public U toEntity(final Z z);

    /**
     * @param u param u
     * @param z param z
     * @return param u
     */
    public U update(U u, final Z z);
}
