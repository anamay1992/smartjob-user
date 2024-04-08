package cl.smartjob.api.user.infrastructure.adapter.input;

import cl.smartjob.api.user.application.model.ErrorResponse;
import cl.smartjob.api.user.application.model.UserRequest;
import cl.smartjob.api.user.application.model.UserResponse;
import cl.smartjob.api.user.application.port.input.UserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "Apis de usuario")
public class UserApiAdapter {

    private final UserUseCase userUseCase;

    @PostMapping("/create")
    @Operation(summary = "Esta api crea un usuario", responses = {
            @ApiResponse(responseCode = "201", description = "Solicitud exitosa",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<UserResponse> createUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del usuario",
                    required = true,
                    content = @Content(schema = @Schema(implementation = UserRequest.class))
            )
            @Valid
            @RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userUseCase.createUser(request));
    }

    @GetMapping("/search")
    @Operation(summary = "Esta api crea un usuario", responses = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<UserResponse> searchUser(@PathParam("email") String email) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userUseCase.searchUser(email));
    }

    @PutMapping("/update")
    @Operation(summary = "Esta api crea un usuario", responses = {
            @ApiResponse(responseCode = "201", description = "Solicitud exitosa",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userUseCase.updateUser(request));
    }

    @DeleteMapping("/drop")
    @Operation(summary = "Esta api crea un usuario", responses = {
            @ApiResponse(responseCode = "204", description = "Solicitud exitosa",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Void> dropUser(@PathParam("email") String email) {
        userUseCase.dropUser(email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
