package cl.smartjob.api.user.infrastructure.adapter.output;

import cl.smartjob.api.user.application.exception.UserException;
import cl.smartjob.api.user.domain.model.User;
import cl.smartjob.api.user.infrastructure.adapter.output.repository.PhoneRepository;
import cl.smartjob.api.user.infrastructure.adapter.output.repository.UserRepository;
import static cl.smartjob.api.user.mock.DataMock.*;
import static cl.smartjob.api.user.mock.DataMock.mockUser;
import static org.junit.jupiter.api.Assertions.*;
import cl.smartjob.api.user.infrastructure.adapter.output.util.ErrorConstant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserPersistenceAdapterTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PhoneRepository phoneRepository;

    @InjectMocks
    private UserPersistenceAdapter userPersistenceAdapter;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(userPersistenceAdapter, "pwdEncrypt", "smartjob");
    }

    @Test
    @DisplayName("Should return success save user")
    public void shouldReturnSuccessSaveUser() {
        when(userRepository.findByEmail(any()))
                .thenReturn(Optional.empty());
        when(userRepository.save(any()))
                .thenReturn(mockUserEntity());
        when(phoneRepository.saveAll(any()))
                .thenReturn(Collections.singletonList(mockPhoneEntity()));
        User user = userPersistenceAdapter.saveUser(mockUser());
        assertEquals(user.getName(), "Angel Leonard Namay Cabanillas");
        assertEquals(user.getEmail(), "angel.n.cabanillas@gmail.com");
        assertEquals(user.getPassword(), "QQskWg0sIH7rpilorU4zCUv4/T6eC1Tw2YSba2DYmcTJIVF7X7aLD9+lfdIZmCH0");
        verify(userRepository, times(1)).findByEmail(any());
        verify(userRepository, times(1)).save(any());
        verify(phoneRepository, times(1)).saveAll(any());
    }

    @Test
    @DisplayName("Should return user exception save user")
    public void shouldReturnUserExceptionSaveUser() {
        when(userRepository.findByEmail(any()))
                .thenReturn(Optional.of(mockUserEntity()));
        UserException exception = assertThrows(UserException.class, () -> {
            userPersistenceAdapter.saveUser(mockUser());
        });
        assertEquals(exception.getMessage(), ErrorConstant.ERROR_EMAIL);
        verify(userRepository, times(1)).findByEmail(any());
    }

    @Test
    @DisplayName("Should return success find user by email")
    public void ShouldReturnSuccessFindUserByEmail() {
        String email = "angel.n.cabanillas@gmail.com";
        when(userRepository.findByEmail(any()))
                .thenReturn(Optional.of(mockUserEntity()));
        when(phoneRepository.findAllByUserId(any()))
                .thenReturn(Collections.singletonList(mockPhoneEntity()));
        User user = userPersistenceAdapter.findUserByEmail(email);
        assertEquals(user.getName(), "Angel Leonard Namay Cabanillas");
        assertEquals(user.getEmail(), "angel.n.cabanillas@gmail.com");
        assertEquals(user.getPassword(), "QQskWg0sIH7rpilorU4zCUv4/T6eC1Tw2YSba2DYmcTJIVF7X7aLD9+lfdIZmCH0");
        assertNotNull(user.getPhones());
        verify(userRepository, times(1)).findByEmail(any());
        verify(phoneRepository, times(1)).findAllByUserId(any());
    }

    @Test
    @DisplayName("Should return user exception find user by email")
    public void ShouldReturnUserExceptionFindUserByEmail() {
        String email = "angel.n.cabanillas2@gmail.com";
        when(userRepository.findByEmail(any()))
                .thenReturn(Optional.empty());
        UserException exception = assertThrows(UserException.class, () -> {
            userPersistenceAdapter.findUserByEmail(email);
        });
        assertEquals(exception.getMessage(), ErrorConstant.ERROR_USER_EXISTS);
        verify(userRepository, times(1)).findByEmail(any());
    }

    @Test
    @DisplayName("Should return success update user")
    public void shouldReturnSuccessUpdateUser() {
        when(userRepository.findByEmail(any()))
                .thenReturn(Optional.of(mockUserEntity()));
        when(userRepository.save(any()))
                .thenReturn(mockUserEntity());
        when(phoneRepository.saveAll(any()))
                .thenReturn(Collections.singletonList(mockPhoneEntity()));
        User user = userPersistenceAdapter.updateUser(mockUser());
        assertEquals(user.getName(), "Angel Leonard Namay Cabanillas");
        assertEquals(user.getEmail(), "angel.n.cabanillas@gmail.com");
        assertEquals(user.getPassword(), "QQskWg0sIH7rpilorU4zCUv4/T6eC1Tw2YSba2DYmcTJIVF7X7aLD9+lfdIZmCH0");
        assertNotNull(user.getPhones());
        verify(userRepository, times(1)).findByEmail(any());
        verify(userRepository, times(1)).save(any());
        verify(phoneRepository, times(1)).saveAll(any());
    }

    @Test
    @DisplayName("Should return user exception update user")
    public void ShouldReturnUserExceptionUpdateUser() {
        when(userRepository.findByEmail(any()))
                .thenReturn(Optional.empty());
        UserException exception = assertThrows(UserException.class, () -> {
            userPersistenceAdapter.updateUser(mockUser());
        });
        assertEquals(exception.getMessage(), ErrorConstant.ERROR_USER_EXISTS);
        verify(userRepository, times(1)).findByEmail(any());
    }

    @Test
    @DisplayName("Should return success delete user")
    public void ShouldReturnSuccessDeleteUser() {
        String email = "angel.n.cabanillas2@gmail.com";
        when(userRepository.findByEmail(any()))
                .thenReturn(Optional.of(mockUserEntity()));
        doNothing().when(userRepository).deleteById(any());
        userPersistenceAdapter.deleteUser(email);
        verify(userRepository, times(1)).findByEmail(any());
    }

    @Test
    @DisplayName("Should return user exception delete user")
    public void ShouldReturnUserExceptionDeleteUser() {
        String email = "angel.n.cabanillas2@gmail.com";
        when(userRepository.findByEmail(any()))
                .thenReturn(Optional.empty());
        UserException exception = assertThrows(UserException.class, () -> {
            userPersistenceAdapter.deleteUser(email);
        });
        assertEquals(exception.getMessage(), ErrorConstant.ERROR_USER_EXISTS);
        verify(userRepository, times(1)).findByEmail(any());
    }

}