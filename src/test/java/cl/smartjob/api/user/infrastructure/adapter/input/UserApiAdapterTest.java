package cl.smartjob.api.user.infrastructure.adapter.input;

import cl.smartjob.api.user.application.model.UserResponse;
import cl.smartjob.api.user.application.port.input.UserUseCase;
import static cl.smartjob.api.user.mock.DataMock.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class UserApiAdapterTest {

    @Mock
    public UserUseCase userService;

    @InjectMocks
    public UserApiAdapter userApiAdapter;

    @Test
    @DisplayName("Should return success create")
    public void shouldReturnSuccessCreate() {
        when(userService.createUser(any()))
                .thenReturn(mockUserResponse());
        ResponseEntity<UserResponse> response = userApiAdapter.createUser(mockUserRequest());
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getName(), "Angel Leonard Namay Cabanillas");
        assertEquals(response.getBody().getEmail(), "angel.n.cabanillas@gmail.com");
        assertEquals(response.getBody().getPassword(), "Angel1234");
        verify(userService, times(1)).createUser(any());
    }

    @Test
    @DisplayName("Should return success search")
    public void shouldReturnSuccessSearch() {
        String email = "angel.n.cabanillas@gmail.com";
        when(userService.searchUser(any()))
                .thenReturn(mockUserResponse());
        ResponseEntity<UserResponse> response = userApiAdapter.searchUser(email);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getName(), "Angel Leonard Namay Cabanillas");
        assertEquals(response.getBody().getEmail(), "angel.n.cabanillas@gmail.com");
        assertEquals(response.getBody().getPassword(), "Angel1234");
        verify(userService, times(1)).searchUser(any());
    }

    @Test
    @DisplayName("Should return success update")
    public void shouldReturnSuccessUpdate() {
        when(userService.updateUser(any()))
                .thenReturn(mockUserResponse());
        ResponseEntity<UserResponse> response = userApiAdapter.updateUser(mockUserRequest());
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getName(), "Angel Leonard Namay Cabanillas");
        assertEquals(response.getBody().getEmail(), "angel.n.cabanillas@gmail.com");
        assertEquals(response.getBody().getPassword(), "Angel1234");
        verify(userService, times(1)).updateUser(any());
    }

    @Test
    @DisplayName("Should return success drop")
    public void shouldReturnSuccessDrop() {
        String email = "angel.n.cabanillas@gmail.com";
        doNothing().when(userService).dropUser(any());
        userApiAdapter.dropUser(email);
        verify(userService, times(1)).dropUser(any());
    }

}