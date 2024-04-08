package cl.smartjob.api.user.domain.service;

import cl.smartjob.api.user.application.model.UserRequest;
import cl.smartjob.api.user.application.model.UserResponse;
import cl.smartjob.api.user.application.port.input.UserUseCase;
import cl.smartjob.api.user.application.port.output.UserPersistence;
import static cl.smartjob.api.user.domain.mapper.DomainMapper.*;

import cl.smartjob.api.user.infrastructure.adapter.output.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserUseCase {

    @Value("${secret.pwdEncrypt}")
    private String pwdEncrypt;

    private final UserPersistence userPersistence;

    @Override
    public UserResponse createUser(UserRequest request) {
        AES256TextEncryptor encryptor = new AES256TextEncryptor();
        encryptor.setPassword(pwdEncrypt);
        request.setPassword(encryptor.encrypt(request.getPassword()));
        UserResponse response = mapperToUserResponse(userPersistence.saveUser(mapperToUser(request)));
        response.setPassword(encryptor.decrypt(response.getPassword()));
        return response;
    }

    @Override
    public UserResponse searchUser(String email) {
        AES256TextEncryptor encryptor = new AES256TextEncryptor();
        encryptor.setPassword(pwdEncrypt);
        UserResponse response = mapperToUserResponse(userPersistence.findUserByEmail(email));
        response.setPassword(encryptor.decrypt(response.getPassword()));
        return response;
    }

    @Override
    public UserResponse updateUser(UserRequest request) {
        AES256TextEncryptor encryptor = new AES256TextEncryptor();
        encryptor.setPassword(pwdEncrypt);
        request.setPassword(encryptor.encrypt(request.getPassword()));
        UserResponse response = mapperToUserResponse(userPersistence.updateUser(mapperToUser(request)));
        response.setPassword(encryptor.decrypt(response.getPassword()));
        return response;
    }

    @Override
    public void dropUser(String email) {
        userPersistence.deleteUser(email);
    }

}
