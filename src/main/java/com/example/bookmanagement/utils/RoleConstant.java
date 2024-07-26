package com.example.bookmanagement.utils;

import java.util.Set;

import com.example.bookmanagement.entities.EPermission;

import static com.example.bookmanagement.entities.EPermission.*;
public class RoleConstant {
     /**
     * A set of permissions granted to admin users.
     */
    public static final Set<EPermission> ADMIN = Set.of(
        PERMISSION_UPDATE_AUTHOR,
        PERMISSION_GET_ALL_AUTHOR,
        PERMISSION_CREATE_AUTHOR,
        PERMISSION_GET_AUTHOR,
        PERMISSION_DELETE_AUTHOR,
        PERMISSION_UPDATE_BOOK,
        PERMISSION_GET_ALL_BOOK,
        PERMISSION_CREATE_BOOK,
        PERMISSION_GET_BOOK,
        PERMISSION_DELETE_BOOK
    );

    /**
     * A set of permissions granted to staff users.
     */
    public static final Set<EPermission> STAFF = Set.of(
        PERMISSION_GET_ALL_AUTHOR,
        PERMISSION_GET_AUTHOR,
        PERMISSION_GET_ALL_BOOK,
        PERMISSION_GET_BOOK
    );

    /**
     * A set of all defined permissions.
     */
    public static final Set<EPermission> LIST_PERMISSIONS = Set.of(
        PERMISSION_UPDATE_AUTHOR,
        PERMISSION_GET_ALL_AUTHOR,
        PERMISSION_CREATE_AUTHOR,
        PERMISSION_GET_AUTHOR,
        PERMISSION_DELETE_AUTHOR,
        PERMISSION_UPDATE_BOOK,
        PERMISSION_GET_ALL_BOOK,
        PERMISSION_CREATE_BOOK,
        PERMISSION_GET_BOOK,
        PERMISSION_DELETE_BOOK
    );
}
