package cl.smartjob.api.user.application.port.output;

import cl.smartjob.api.user.domain.model.User;

public interface UserPersistence {

    User saveUser(User user);
    User findUserByEmail(String email);
    User updateUser(User user);
    void deleteUser(String email);

}
