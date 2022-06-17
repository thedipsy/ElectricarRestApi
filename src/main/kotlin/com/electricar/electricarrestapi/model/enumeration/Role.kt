package com.electricar.electricarrestapi.model.enumeration

import org.springframework.security.core.GrantedAuthority

enum class Role : GrantedAuthority {
    ROLE_USER {
        override fun getAuthority(): String = name
    },
    ROLE_ADMIN {
        override fun getAuthority(): String = name
    }
}
