package com.soft.srms.model;

import java.io.Serializable;

/**
 * This enumeration represents all gender options available during dormitory application process.
 * The following gender options are:
 * <ul>
 *     <li>{@link #MALE}<br> The male gender.
 *     </li>
 *     <li>{@link #FEMALE}<br> The female gender.
 *     </li>
 * </ul>
 */
public enum Gender implements Serializable {
    /**
     * Denotes the male gender.
     */
    MALE,
    /**
     * Denotes the female gender.
     */
    FEMALE
}
