package uz.ataboyev.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.ClientReqDto;
import uz.ataboyev.warehouse.service.ClientService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ClientControllerImpl implements ClientController{

    private final ClientService clientService;

    @Override
    public ApiResult<?> add(@Valid ClientReqDto clientReqDto) {
        return clientService.add(clientReqDto);
    }

    @Override
    public ApiResult<?> getOne(Long clientId) {
        return clientService.getOne(clientId);
    }

    @Override
    public ApiResult<?> getAllByCompanyId(Long companyId) {
        return clientService.getAllByCompanyId(companyId);
    }

    @Override
    public ApiResult<?> edit(Long clientId, ClientReqDto clientReqDto) {
        return clientService.edit(clientId,clientReqDto);
    }

    @Override
    public ApiResult<?> delete(Long clientId) {
        return clientService.delete(clientId);
    }
}