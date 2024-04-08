package cl.smartjob.api.user.infrastructure.adapter.output.util;

import cl.smartjob.api.user.domain.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class Util {

    public static String generateToken(User user, String secret) {
        Algorithm algorithm = Algorithm.HMAC512(secret);
        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("name", user.getName())
                .withClaim("password", user.getPassword())
                .sign(algorithm);
    }

}
