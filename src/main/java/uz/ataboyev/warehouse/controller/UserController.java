package uz.ataboyev.warehouse.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.ataboyev.warehouse.aop.annotation.CheckPermission;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.UserDtoByRole;
import uz.ataboyev.warehouse.payload.UserRoleDto;
import uz.ataboyev.warehouse.service.UserService;
import uz.ataboyev.warehouse.utils.RestConstant;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(RestConstant.USER_CONTROLLER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PreAuthorize(value = "hasAnyAuthority('GET_USER_LIST')")
    @GetMapping("/get-all-by-role/{roleId}")
    ApiResult<List<UserDtoByRole>>getAllByRole(@PathVariable Long roleId){
        return userService.getAllByRole(roleId);
    }

    @PreAuthorize(value = "hasAnyAuthority('DELETE_USER')")
    @DeleteMapping("/delete/{id}")
    ApiResult<?>delete(@PathVariable UUID id){
        return userService.delete(id);
    }

    //todo edit user role
    //BU YO'L ADMIN UCHUN, ADMIN ISHCHILARIGA ROLE PERMISSIONLARNI SHU YO'L ORQALI BIRIKTIRADI
    @PreAuthorize(value = "hasAnyAuthority('EDIT_USER_ROLE')")
    @PutMapping("/edit/by-user")
    ApiResult<?>editUserRole(@RequestBody UserRoleDto userRoleDto){
        return userService.editUserRole(userRoleDto);
    }

//    @PreAuthorize(value = "hasAnyAuthority('EDIT_USER')")
//    @PutMapping("/edit/{id}")
//    ApiResult<?>editUser(@PathVariable UUID id,@RequestBody UserDto userDto){
//        return userService.editUser(id,userDto);
//    }


    @CheckPermission(values = {"GET_USER","GET"})
    @GetMapping("get-one/{id}")
    ApiResult<?>getOne(@PathVariable UUID id){
        return userService.getOne(id);
    }
}
