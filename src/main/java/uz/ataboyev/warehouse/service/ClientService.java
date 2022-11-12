package uz.ataboyev.warehouse.service;

import uz.ataboyev.warehouse.payload.*;

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

    List<ClientHistoryDto> clientHistory(Long clientId);

}
