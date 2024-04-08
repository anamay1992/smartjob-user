package cl.smartjob.api.user.application.model;

import cl.smartjob.api.user.domain.model.Phone;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class UserResponse {

    @Schema(description = "Id del ususario en la BD",
            example = "1")
    private UUID id;

    @Schema(description = "Nombre del usuario",
            example = "Angel Leonard Namay Cabanillas")
    private String name;

    @Schema(description = "Correo del usuario",
            example = "angel.n.cabanillas@gmail.com")
    private String email;

    @Schema(description = "Password del usuario",
            example = "Angel1234")
    private String password;

    private List<Phone> phones;

    @Schema(description = "Fecha de creacion del usuario",
            example = "2024-04-08")
    private LocalDate created;

    @Schema(description = "Fecha de actualizacion del usuario",
            example = "2024-04-08")
    private LocalDate modified;

    @Schema(description = "Fecha de ultimo login del ususario",
            example = "2024-04-08")
    private LocalDate lastLogin;

    @Schema(description = "Token del usuario",
            example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhb" +
                    "mdlbC5uLmNhYmFuaWxsYXNAZ21haWwuY29tIiwicGFzc3dvcmQiO" +
                    "iI0ZytvVHRyWHBBbTRGNUZxQjF5aDZ0NDVRT3NZNU1pcEJCTVZMc" +
                    "m84d3NveUpLU1d2N3RDZHRlMzNseWcyWW5GIiwibmFtZSI6IkFuZ" +
                    "2VsIExlb25hcmQgTmFtYXkgQ2FiYW5pbGxhcyJ9.Sejm0SDpvwol" +
                    "bEib7Y2S6QfUfIB8ep6eOgRhplt_XkF_y6W4Gxo0QLLSLdD8Ybke" +
                    "qpOhJeCYK0VZrZdN0ttK1Q")
    private String token;

    @Schema(description = "Estado del usuario",
            example = "true")
    private Boolean active;

}
