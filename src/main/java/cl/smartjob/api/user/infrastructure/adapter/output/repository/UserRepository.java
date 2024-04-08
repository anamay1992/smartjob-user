package cl.smartjob.api.user.infrastructure.adapter.output.repository;

import cl.smartjob.api.user.infrastructure.adapter.output.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

}
