package cl.smartjob.api.user.mock;

import cl.smartjob.api.user.application.model.UserRequest;
import cl.smartjob.api.user.application.model.UserResponse;
import cl.smartjob.api.user.domain.model.Phone;
import cl.smartjob.api.user.domain.model.User;
import static cl.smartjob.api.user.domain.util.Util.*;
import cl.smartjob.api.user.infrastructure.adapter.output.entity.PhoneEntity;
import cl.smartjob.api.user.infrastructure.adapter.output.entity.UserEntity;
import java.time.LocalDate;
import java.util.Collections;

public class DataMock {

    public static UserResponse mockUserResponse() {
        UserResponse response = new UserResponse();
        response.setId(generateUUID());
        response.setName("Angel Leonard Namay Cabanillas");
        response.setEmail("angel.n.cabanillas@gmail.com");
        response.setPassword("Angel1234");
        response.setPhones(Collections.singletonList(mockPhone()));
        response.setCreated(LocalDate.now());
        response.setModified(LocalDate.now());
        response.setLastLogin(LocalDate.now());
        response.setToken(mockToken());
        response.setActive(true);
        return response;
    }

    public static Phone mockPhone() {
        Phone phone = new Phone();
        phone.setNumber("123456789");
        phone.setCityCode("1");
        phone.setCountryCode("57");
        return phone;
    }

    public static String mockToken() {
        return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJh" +
                "bmdlbC5uLmNhYmFuaWxsYXNAZ21haWwuY29tIiwicGFzc3dv" +
                "cmQiOiI0ZytvVHRyWHBBbTRGNUZxQjF5aDZ0NDVRT3NZNU1p" +
                "cEJCTVZMcm84d3NveUpLU1d2N3RDZHRlMzNseWcyWW5GIiwi" +
                "bmFtZSI6IkFuZ2VsIExlb25hcmQgTmFtYXkgQ2FiYW5pbGxh" +
                "cyJ9.Sejm0SDpvwolbEib7Y2S6QfUfIB8ep6eOgRhplt_XkF" +
                "_y6W4Gxo0QLLSLdD8YbkeqpOhJeCYK0VZrZdN0ttK1Q";
    }

    public static UserRequest mockUserRequest() {
        UserRequest request = new UserRequest();
        request.setName("Angel Leonard Namay Cabanillas");
        request.setEmail("angel.n.cabanillas@gmail.com");
        request.setPassword("Angel1234");
        request.setPhones(Collections.singletonList(mockPhone()));
        return request;
    }

    public static User mockUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Angel Leonard Namay Cabanillas");
        user.setEmail("angel.n.cabanillas@gmail.com");
        user.setPassword("QQskWg0sIH7rpilorU4zCUv4/T6eC1Tw2YSba2DYmcTJIVF7X7aLD9+lfdIZmCH0");
        user.setPhones(Collections.singletonList(mockPhone()));
        user.setCreated(LocalDate.now());
        user.setModified(LocalDate.now());
        user.setLastLogin(LocalDate.now());
        user.setToken(mockToken());
        user.setActive(true);
        return user;
    }

    public static UserEntity mockUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("Angel Leonard Namay Cabanillas");
        userEntity.setEmail("angel.n.cabanillas@gmail.com");
        userEntity.setPassword("QQskWg0sIH7rpilorU4zCUv4/T6eC1Tw2YSba2DYmcTJIVF7X7aLD9+lfdIZmCH0");
        userEntity.setPhones(Collections.singletonList(mockPhoneEntity()));
        userEntity.setCreated(LocalDate.now());
        userEntity.setModified(LocalDate.now());
        userEntity.setLastLogin(LocalDate.now());
        userEntity.setToken(mockToken());
        userEntity.setActive(true);
        return userEntity;
    }

    public static PhoneEntity mockPhoneEntity() {
        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.setNumber("123456789");
        phoneEntity.setCityCode("1");
        phoneEntity.setCountryCode("57");
        return phoneEntity;
    }

}
