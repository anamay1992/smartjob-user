package cl.smartjob.api.user.domain.model;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
    private List<Phone> phones;
    private LocalDate created;
    private LocalDate modified;
    private LocalDate lastLogin;
    private String token;
    private Boolean active;

}
