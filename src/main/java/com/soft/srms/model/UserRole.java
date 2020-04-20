package com.soft.srms.model;

import java.io.Serializable;

/**
 * This enumeration represents all user roles in the application.
 * The following user roles are:
 * <ul>
 *     <li>{@link #ROLE_STUDENT}<br> Applicant for dormitory housing.
 *     </li>
 *     <li>{@link #ROLE_TENANT}<br> Dormitory tenant.
 *     </li>
 *     <li>{@link #ROLE_ADMIN}<br> Dormitory supervisor.
 *     </li>
 * </ul>
 */
public enum UserRole implements Serializable {
    /**
     * Dormitory applicant with limited access to student resources.
     */
    ROLE_STUDENT,
    /**
     * Dormitory tenant with full access to student resources.
     */
    ROLE_TENANT,
    /**
     * Dormitory supervisor with administrative capabilities.
     */
    ROLE_ADMIN
}
