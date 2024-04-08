package cl.smartjob.api.user.infrastructure.adapter.output.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneEntity> phones;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate created;

    @UpdateTimestamp
    private LocalDate modified;

    @CreationTimestamp
    private LocalDate lastLogin;

    @Column(length = 500)
    private String token;

    private Boolean active;

    public void addPhone(PhoneEntity phone) {
        boolean exists = this.phones.stream()
                .anyMatch(p ->
                        p.getNumber().equals(phone.getNumber()));
        if (!exists) {
            this.phones.add(phone);
            phone.setUser(this);
        } else {
            throw new RuntimeException();
        }
    }

    public void removePhone(PhoneEntity phone) {
        boolean wasRemoved = this.phones.remove(phone);
        if (wasRemoved) {
            phone.setUser(null);
        } else {
            throw new RuntimeException();
        }
    }

}
