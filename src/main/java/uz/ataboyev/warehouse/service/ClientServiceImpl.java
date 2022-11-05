package uz.ataboyev.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.ataboyev.warehouse.payload.ClientDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{
    

    @Override
    public List<ClientDTO> getAll() {
        return null;
    }

    @Override
    public void checkingClientByIdListOrElseThrow(List<Long> clientIdList) {

        // TODO: 06/11/22  
        
    }
}
