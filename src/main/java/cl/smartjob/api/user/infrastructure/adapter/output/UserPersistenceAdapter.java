package cl.smartjob.api.user.infrastructure.adapter.output;

import cl.smartjob.api.user.application.exception.UserException;
import cl.smartjob.api.user.infrastructure.adapter.output.entity.PhoneEntity;
import cl.smartjob.api.user.infrastructure.adapter.output.util.ErrorConstant;
import cl.smartjob.api.user.infrastructure.adapter.output.util.Util;
import cl.smartjob.api.user.domain.model.User;
import cl.smartjob.api.user.application.port.output.UserPersistence;
import cl.smartjob.api.user.infrastructure.adapter.output.entity.UserEntity;
import cl.smartjob.api.user.infrastructure.adapter.output.repository.PhoneRepository;
import cl.smartjob.api.user.infrastructure.adapter.output.repository.UserRepository;
import static cl.smartjob.api.user.infrastructure.mapper.InfrastructureMapper.*;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPersistence {

    @Value("${secret.pwdEncrypt}")
    private String pwdEncrypt;

    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;

    @Override
    public User saveUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserException(ErrorConstant.ERROR_EMAIL);
        }
        user.setToken(Util.generateToken(user, pwdEncrypt));
        UserEntity saveUserEntity = userRepository.save(mapperToUserEntity(user));
        saveUserEntity.setPhones(phoneRepository.saveAll(mapperToListPhoneEntities(saveUserEntity, user.getPhones())));
        return mapperToUser(saveUserEntity);
    }

    @Override
    public User findUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(ErrorConstant.ERROR_USER_EXISTS));
        List<PhoneEntity> phoneEntities = phoneRepository.findAllByUserId(userEntity.getId());
        userEntity.setPhones(phoneEntities);
        return mapperToUser(userEntity);
    }

    @Override
    public User updateUser(User user) {
        UserEntity userEntity = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new UserException(ErrorConstant.ERROR_USER_EXISTS));
        user.setToken(Util.generateToken(user, pwdEncrypt));
        UserEntity saveUserEntity = userRepository.save(mapperToUserEntity(userEntity, user));
        saveUserEntity.setPhones(phoneRepository.saveAll(mapperToListPhoneEntities(saveUserEntity, user.getPhones())));
        return mapperToUser(saveUserEntity);
    }

    @Override
    public void deleteUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(ErrorConstant.ERROR_USER_EXISTS));
        userRepository.deleteById(userEntity.getId());
    }

}
