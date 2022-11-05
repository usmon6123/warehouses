package uz.ataboyev.warehouse.service;

import uz.ataboyev.warehouse.payload.ClientDTO;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getAll();

    void checkingClientByIdListOrElseThrow(List<Long> clientIdList);
}
