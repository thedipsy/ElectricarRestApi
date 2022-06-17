package com.electricar.electricarrestapi.config

import com.electricar.electricarrestapi.model.entity.User
import io.jsonwebtoken.*
import org.springframework.stereotype.Component
import java.util.*


@Component
class JwtTokenUtil {

    companion object {
        private const val EXPIRE_DURATION = (24 * 60 * 60 * 1000).toLong() // 24 hour
        private const val SECRET_KEY: String = "SECRET"
    }

    fun generateAccessToken(user: User): String {
        return Jwts.builder()
            .setSubject(java.lang.String.format("%s", user.username))
            .setIssuer("Electricar")
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + EXPIRE_DURATION))
            .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
            .compact()
    }

    fun validateAccessToken(token: String): Boolean {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ex: ExpiredJwtException) {
            ex.printStackTrace() //JWT expired
        } catch (ex: IllegalArgumentException) {
            ex.printStackTrace() //Token is null, empty or only whitespace
        } catch (ex: MalformedJwtException) {
            ex.printStackTrace() //JWT is invalid
        } catch (ex: UnsupportedJwtException) {
            ex.printStackTrace()  //JWT is not supported
        } catch (ex: SignatureException) {
            ex.printStackTrace() //Signature validation failed
        }
        return false;
    }
    
    fun getSubject(token : String) : String
        = parseClaims(token).subject;
    
    private fun parseClaims(token : String) : Claims
        = Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .body;

}