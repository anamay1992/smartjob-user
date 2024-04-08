package cl.smartjob.api.user.domain.service;

import cl.smartjob.api.user.application.model.UserResponse;
import cl.smartjob.api.user.application.port.output.UserPersistence;
import static cl.smartjob.api.user.mock.DataMock.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserPersistence userPersistence;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(userService, "pwdEncrypt", "smartjob");
    }

    @Test
    @DisplayName("Should return success create user")
    public void shouldReturnSuccessCreateUser() {
        when(userPersistence.saveUser(any()))
                .thenReturn(mockUser());
        UserResponse response = userService.createUser(mockUserRequest());
        assertNotNull(response);
        assertEquals(response.getName(), "Angel Leonard Namay Cabanillas");
        assertEquals(response.getEmail(), "angel.n.cabanillas@gmail.com");
        assertEquals(response.getPassword(), "Angel1234");
        verify(userPersistence, times(1)).saveUser(any());
    }

    @Test
    @DisplayName("Should return success search user")
    public void shouldReturnSuccessSearchUser() {
        String email = "angel.n.cabanillas@gmail.com";
        when(userPersistence.findUserByEmail(any()))
                .thenReturn(mockUser());
        UserResponse response = userService.searchUser(email);
        assertNotNull(response);
        assertEquals(response.getName(), "Angel Leonard Namay Cabanillas");
        assertEquals(response.getEmail(), "angel.n.cabanillas@gmail.com");
        assertEquals(response.getPassword(), "Angel1234");
        verify(userPersistence, times(1)).findUserByEmail(any());
    }

    @Test
    @DisplayName("Should return success update user")
    public void shouldReturnSuccessUpdateUser() {
        when(userPersistence.updateUser(any()))
                .thenReturn(mockUser());
        UserResponse response = userService.updateUser(mockUserRequest());
        assertNotNull(response);
        assertEquals(response.getName(), "Angel Leonard Namay Cabanillas");
        assertEquals(response.getEmail(), "angel.n.cabanillas@gmail.com");
        assertEquals(response.getPassword(), "Angel1234");
        verify(userPersistence, times(1)).updateUser(any());
    }

    @Test
    @DisplayName("Should return success drop user")
    public void shouldReturnSuccessDropUser() {
        String email = "angel.n.cabanillas@gmail.com";
        doNothing().when(userPersistence).deleteUser(any());
        userService.dropUser(email);
        verify(userPersistence, times(1)).deleteUser(any());
    }

}