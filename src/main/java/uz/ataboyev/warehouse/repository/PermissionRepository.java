package uz.ataboyev.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.ataboyev.warehouse.entity.Permission;
import uz.ataboyev.warehouse.enums.PermissionEnum;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Optional<Permission> findByNameEnum(PermissionEnum nameEnum);

    @Query(value = "select *from  permission p where p.name_enum in(:permissions)", nativeQuery = true)
    List<Permission> getPermissionList(@Param("permissions") List<PermissionEnum> permissionEnums);

    Set<Permission> findByNameEnumIn(Set<PermissionEnum> nameEnum);

}
