package com.electricar.electricarrestapi.model.entity

import com.electricar.electricarrestapi.model.enumeration.Role
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

import javax.persistence.*

@Entity
@Table(name = "ec_user")
data class User(
    @Id
    private val username: String = "",
    private var password: String = "",
    val name: String = "",
    val surname: String = "",
    val phone: String = "",

    @Enumerated(EnumType.STRING)
    val role: Role = Role.ROLE_USER,

    //from UserDetails
    private val isEnabled: Boolean = true,
    private val isCredentialsNonExpired: Boolean = true,
    private val isAccountNonExpired: Boolean = true,
    private val isAccountNonLocked: Boolean = true,

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var cars: List<Car> = emptyList(),

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var reservations: List<Reservation> = emptyList()

) : UserDetails {
    override fun getUsername(): String = username
    override fun getPassword(): String = password

    override fun isEnabled(): Boolean = isEnabled
    override fun isCredentialsNonExpired(): Boolean = isCredentialsNonExpired
    override fun isAccountNonExpired(): Boolean = isAccountNonExpired
    override fun isAccountNonLocked(): Boolean = isAccountNonLocked
    override fun getAuthorities(): Collection<GrantedAuthority> = AuthorityUtils.createAuthorityList(role.toString())
    override fun toString(): String = "$name $surname"
}







