package cl.smartjob.api.user.domain.mapper;

import cl.smartjob.api.user.application.model.UserRequest;
import cl.smartjob.api.user.application.model.UserResponse;
import cl.smartjob.api.user.domain.model.User;
import static cl.smartjob.api.user.domain.util.Util.*;

import java.util.UUID;

public class DomainMapper {

    public static User mapperToUser(UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhones(request.getPhones());
        return user;
    }

    public static UserResponse mapperToUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(generateUUID());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPassword(user.getPassword());
        response.setPhones(user.getPhones());
        response.setCreated(user.getCreated());
        response.setModified(user.getModified());
        response.setLastLogin(user.getLastLogin());
        response.setToken(user.getToken());
        response.setActive(user.getActive());
        return response;
    }

}
