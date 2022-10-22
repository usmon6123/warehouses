package uz.ataboyev.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.ataboyev.warehouse.enums.PermissionEnum;
import uz.ataboyev.warehouse.payload.*;
import uz.ataboyev.warehouse.repository.RolePermissionFromUserRepository;
import uz.ataboyev.warehouse.repository.RoleRepository;
import uz.ataboyev.warehouse.repository.UserRepository;
import uz.ataboyev.warehouse.service.base.BaseService;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PermissionService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BaseService baseService;
    private final RolePermissionFromUserRepository rolePermissionFromUserRepository;


    public ApiResult<?> getAll() {

        PermissionEnum[] permissionEnums = PermissionEnum.values();

        Set<PermissionDto> permissionDtos = Arrays.stream(permissionEnums).map(PermissionDto::new).collect(Collectors.toSet());

        return ApiResult.successResponse(permissionDtos);

    }


}
