package uz.ataboyev.warehouse.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.ataboyev.warehouse.aop.annotation.CheckPermission;
import uz.ataboyev.warehouse.exception.RestException;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.LoginDto;
import uz.ataboyev.warehouse.payload.SignUpDto;
import uz.ataboyev.warehouse.payload.TokenDto;
import uz.ataboyev.warehouse.service.AuthService;
import uz.ataboyev.warehouse.utils.RestConstant;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(RestConstant.AUTH_CONTROLLER)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ApiResult<?> signUp (@RequestBody @Valid SignUpDto signUpDto){
        log.info("entered sign-up {}", signUpDto);
        ApiResult<?> result = authService.signUp(signUpDto);
        log.info("finished sign-up {}", result);
        return result;
    }

    @PostMapping("/sign-in")
    public ApiResult<?> signIn(@RequestBody @Valid LoginDto loginDto){

        return authService.signIn(loginDto);
    }

    @PostMapping("/refresh-token")
    public ApiResult<TokenDto> refreshToken(@RequestBody TokenDto tokenDto){
        return authService.refreshToken(tokenDto);
    };


//----------------------------------TEST---------------------------------------------------

    @GetMapping("/get")
    public HttpEntity<?>get(){

        try {
            return ResponseEntity.ok("get yo'li");
        }catch (Exception e){
            throw new RestException(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @PreAuthorize(value = "hasAnyAuthority('ALL','ADD','UPDATE','DELETE','GET')")
    @PostMapping("/test-1")
    public HttpEntity<?>test1(){
        return ResponseEntity.ok("test 1 yo'li :) P= hammasi");
    }

//    @PreAuthorize(value = "hasAnyAuthority('ALL')")//PASTDAGI CHECK_PERMISSION O'ZIMIZ YASAGAN ANNOTATSIYAMIZ BU ANNOTATSIYANING VAZIFASINI QILIBERADI
    @CheckPermission(values = {"GET","ADD","ALL"})
    @PostMapping("/test-2")
    public HttpEntity<?>test2(){
        return ResponseEntity.ok("test 2 yo'li :) P=ALL  GET ADD");
    }

//    @PreAuthorize(value = "hasAnyAuthority('GET')")
    @CheckPermission(values = "ALL")
    @PostMapping("/test-3")
    public HttpEntity<?>test3(){
        try {
            return ResponseEntity.ok("test 3 yo'li :) P=GET");
        }catch (Exception e){
            throw new RestException(e.getMessage(), HttpStatus.FORBIDDEN);
        }

    }
//----------------------------------TEST---------------------------------------------------


}
