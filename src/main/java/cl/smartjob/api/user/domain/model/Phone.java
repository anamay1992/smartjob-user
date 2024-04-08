package cl.smartjob.api.user.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Phone {

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[0-9]+$")
    @Schema(description = "Numero de telefono",
            example = "123456789")
    private String number;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[0-9]+$")
    @Schema(description = "Codigo de ciudad",
            example = "1")
    private String cityCode;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[0-9]+$")
    @Schema(description = "Codigo de pais",
            example = "57")
    private String countryCode;

}
