package cl.smartjob.api.user.application.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ErrorResponse {

    @Schema(description = "Mensaje de error",
            example = "El correo ingresado no tiene un formato correcto.")
    private String message;

}
