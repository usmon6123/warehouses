package uz.ataboyev.warehouse.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.ataboyev.warehouse.entity.Client;
import uz.ataboyev.warehouse.payload.ClientHistoryDto;

import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {
    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);

    boolean existsById(Long id);

//    @SqlResultSetMapping(name = "mapClientHistoryDto",
//            classes = @ConstructorResult(targetClass = ClientHistoryDto.class,columns = )
//                        )
//    ClientHistoryDto clientHistoryDto(@Param("clientId")Long clientId);



}
