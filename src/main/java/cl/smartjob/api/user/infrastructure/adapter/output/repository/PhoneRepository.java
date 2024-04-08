package cl.smartjob.api.user.infrastructure.adapter.output.repository;

import cl.smartjob.api.user.infrastructure.adapter.output.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneRepository extends JpaRepository<PhoneEntity, Long> {

    List<PhoneEntity> findAllByUserId(Long id);

}
