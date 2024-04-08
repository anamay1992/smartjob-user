package cl.smartjob.api.user.infrastructure.mapper;

import cl.smartjob.api.user.domain.model.Phone;
import cl.smartjob.api.user.domain.model.User;
import cl.smartjob.api.user.infrastructure.adapter.output.entity.PhoneEntity;
import cl.smartjob.api.user.infrastructure.adapter.output.entity.UserEntity;
import java.util.List;
import java.util.stream.Collectors;

public class InfrastructureMapper {

    public static UserEntity mapperToUserEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setToken(user.getToken());
        userEntity.setActive(true);
        return userEntity;
    }

    public static UserEntity mapperToUserEntity(UserEntity userEntity, User user) {
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setToken(user.getToken());
        userEntity.setActive(true);
        return userEntity;
    }

    public static PhoneEntity mapperToPhoneEntity(UserEntity userEntity, Phone phone) {
        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.setNumber(phone.getNumber());
        phoneEntity.setCityCode(phone.getCityCode());
        phoneEntity.setCountryCode(phone.getCountryCode());
        phoneEntity.setUser(userEntity);
        return phoneEntity;
    }

    public static List<PhoneEntity> mapperToListPhoneEntities(UserEntity userEntity, List<Phone> phones) {
        return phones.stream()
                .map(phone -> mapperToPhoneEntity(userEntity, phone))
                .collect(Collectors.toList());
    }

    public static Phone mapperToPhone(PhoneEntity phoneEntity) {
        Phone phone = new Phone();
        phone.setNumber(phoneEntity.getNumber());
        phone.setCityCode(phoneEntity.getCityCode());
        phone.setCountryCode(phoneEntity.getCountryCode());
        return phone;
    }

    public static List<Phone> mapperToListPhone(List<PhoneEntity> phoneEntities) {
        return phoneEntities.stream()
                .map(InfrastructureMapper::mapperToPhone)
                .collect(Collectors.toList());
    }

    public static User mapperToUser(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setName(userEntity.getName());
        user.setEmail(userEntity.getEmail());
        user.setPassword(userEntity.getPassword());
        user.setPhones(mapperToListPhone(userEntity.getPhones()));
        user.setCreated(userEntity.getCreated());
        user.setModified(userEntity.getModified());
        user.setLastLogin(userEntity.getLastLogin());
        user.setToken(userEntity.getToken());
        user.setActive(userEntity.getActive());
        return user;
    }

}
