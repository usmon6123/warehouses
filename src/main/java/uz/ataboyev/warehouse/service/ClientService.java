package uz.ataboyev.warehouse.service;

import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.ClientReqDto;
import uz.ataboyev.warehouse.payload.ClientResDto;
import uz.ataboyev.warehouse.payload.OptionResDto;

import java.util.List;

public interface ClientService {

    List<ClientResDto> getAll();

    void checkingClientByIdListOrElseThrow(List<Long> clientIdList);

    ApiResult<?> add(ClientReqDto clientReqDto);

    ApiResult<?> edit(Long clientId, ClientReqDto clientReqDto);

    ApiResult<?> getOne(Long clientId);

    ApiResult<?> getAllClient();

    ApiResult<?> delete(Long clientId);


    List<OptionResDto> getClientsForOption();

}
