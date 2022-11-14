package uz.ataboyev.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.ataboyev.warehouse.entity.Client;
import uz.ataboyev.warehouse.payload.clientDtos.ClientBalance;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {
    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);

    boolean existsById(Long id);



    @Query(value = "select  c.client_type as clientType, " +
            "CAST (c.id as varchar) as clientId, "+
            "c.name as clientName, " +
            "SUM(o.order_price_dollar) as balanceDollar," +
            "SUM(o.order_price_sum) as balanceSum from client c " +
            "inner join orders o on c.id = o.client_id " +
            "where (o.warehouse_id = :warehouseId) " +
            "group by c.name,c.id, c.client_type",nativeQuery = true)
    List<ClientBalance> getALLClientBalance(@Param("warehouseId")Long warehouseId);


//    @SqlResultSetMapping(name = "mapClientHistoryDto",
//            classes = @ConstructorResult(targetClass = ClientHistoryDto.class,columns = )
//                        )
//    ClientHistoryDto clientHistoryDto(@Param("clientId")Long clientId);



}
