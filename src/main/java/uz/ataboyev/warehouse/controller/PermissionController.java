package uz.ataboyev.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ataboyev.warehouse.aop.annotation.CheckPermission;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.service.PermissionService;
import uz.ataboyev.warehouse.utils.RestConstant;

@RestController
@RequiredArgsConstructor
@RequestMapping(RestConstant.PERMISSION_CONTROLLER)
public class PermissionController {
    private final PermissionService permissionService;

    @CheckPermission(values = {"GET_ROLES"})
    @GetMapping("/get-all")
    ApiResult<?>getAll(){
       return permissionService.getAll();
    }
}
