package cl.smartjob.api.user.application.port.input;

import cl.smartjob.api.user.application.model.UserRequest;
import cl.smartjob.api.user.application.model.UserResponse;

public interface UserUseCase {

    UserResponse createUser(UserRequest request);
    UserResponse searchUser(String email);
    UserResponse updateUser(UserRequest request);
    void dropUser(String email);

}
