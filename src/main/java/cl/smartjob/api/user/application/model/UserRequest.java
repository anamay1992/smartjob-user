package cl.smartjob.api.user.application.model;

import cl.smartjob.api.user.domain.model.Phone;
import cl.smartjob.api.user.infrastructure.adapter.output.util.ValidPassword;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
public class UserRequest {

    @NotNull
    @NotEmpty
    @Schema(description = "Nombre del usuario",
            example = "Angel Leonard Namay Cabanillas")
    private String name;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "El correo ingresado no tiene un formato correcto.")
    @Schema(description = "Email del usuario",
            example = "angel.n.cabanillas@gmail.com")
    private String email;

    @NotNull
    @NotEmpty
    @ValidPassword
    @Schema(description = "Password del usuario",
            example = "Angel1234")
    private String password;

    @NotEmpty
    @Valid
    @ArraySchema(arraySchema = @Schema(description = "Lista de telefonos del usuario"),
            schema = @Schema(implementation = Phone.class))
    private List<Phone> phones;

}
