package uz.ataboyev.warehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.ataboyev.warehouse.payload.*;
import uz.ataboyev.warehouse.utils.RestConstant;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(path = RestConstant.CLIENT_CONTROLLER)
public interface ClientController {

    @PostMapping("/add")
    ApiResult<?> add(@RequestBody @Valid ClientReqDto clientReqDto);

    @GetMapping("/get-one/{clientId}")
    ApiResult<?> getOne(@PathVariable Long clientId);

    @GetMapping("/get-all-clients")
    ApiResult<?> getAllClient();

    @GetMapping("/get-clients-for-option")
    List<OptionResDto>getClients();

    @PutMapping("/edit/{clientId}")
    ApiResult<?> edit(@PathVariable Long clientId, @RequestBody ClientReqDto clientReqDto);

    @DeleteMapping("/delete/{clientId}")
    ApiResult<?> delete(@PathVariable Long clientId);

    @GetMapping("client-history/{clientId}")
    List<ClientHistoryDto>clientHistory(Long clientId);

}
