package com.electricar.electricarrestapi.config.filters

import com.electricar.electricarrestapi.config.JwtTokenUtil
import com.electricar.electricarrestapi.model.jwt.JWTUser
import com.electricar.electricarrestapi.model.jwt.JwtAuthConstants
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.util.ObjectUtils
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class JWTTokenFilter(private val jwtUtil: JwtTokenUtil) : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse, filterChain: FilterChain
    ) {
        if (!hasAuthorizationBearer(request)) {
            filterChain.doFilter(request, response)
            return
        }

        val token = getAccessToken(request)
        if (!jwtUtil.validateAccessToken(token)) {
            filterChain.doFilter(request, response)
            return
        }

        setAuthenticationContext(token, request)
        filterChain.doFilter(request, response)
    }

    private fun hasAuthorizationBearer(request: HttpServletRequest): Boolean {
        val header = request.getHeader(JwtAuthConstants.HEADER_STRING)
        return !(ObjectUtils.isEmpty(header) || !header.startsWith(JwtAuthConstants.TOKEN_PREFIX))
    }

    private fun getAccessToken(request: HttpServletRequest): String {
        val header = request.getHeader(JwtAuthConstants.HEADER_STRING)
        return header.replace(JwtAuthConstants.TOKEN_PREFIX, "")
    }

    private fun setAuthenticationContext(token: String, request: HttpServletRequest) {
        val userDetails = getUserDetails(token)
        val authentication = UsernamePasswordAuthenticationToken(userDetails.username, null, null)

        authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authentication
    }

    private fun getUserDetails(token: String): JWTUser
    = JWTUser(jwtUtil.getSubject(token))

}
