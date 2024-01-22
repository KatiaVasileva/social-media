package ru.personal.task.socialmedia.security.jwt;

import io.jsonwebtoken.Claims;
import lombok.NoArgsConstructor;
import ru.personal.task.socialmedia.entity.Role;

@NoArgsConstructor
public class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRole(getRole(claims));
        jwtInfoToken.setUsername(claims.getSubject());
        return jwtInfoToken;
    }

    public static Role getRole (Claims claims) {
        return claims.get("role", Role.class);
    }

}
