package uz.ataboyev.warehouse.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.ataboyev.warehouse.entity.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {
    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);

    boolean existsById(Long id);


}
