package uz.ataboyev.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ataboyev.warehouse.entity.Client;
import uz.ataboyev.warehouse.exception.RestException;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.ClientReqDto;
import uz.ataboyev.warehouse.payload.ClientResDto;
import uz.ataboyev.warehouse.repository.ClientRepository;
import uz.ataboyev.warehouse.service.base.BaseService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;
    private final BaseService baseService;

    @Override
    public ApiResult<?> add(ClientReqDto clientReqDto) {

        checkingClientByPhoneNumberOrElseThrow(clientReqDto.getPhoneNumber());

        Client client = Client.make(clientReqDto);

        saveClient(client);

        ClientResDto clientResDto = mapClient(client);

        return ApiResult.successResponse(clientResDto,"success aded");
    }

    @Override
    public ApiResult<?> edit(Long clientId, ClientReqDto clientReqDto) {

        //CLIENTNI BAZADAN OLIB KELADI TOPOLMASA THROW
        Client client = baseService.getClientById(clientId);

        updateClient(client,clientReqDto);

        saveClient(client);

        ClientResDto clientResDto = mapClient(client);

        return ApiResult.successResponse(clientResDto);
    }

    @Override
    public ApiResult<?> getOne(Long clientId) {
        return null;
    }

    @Override
    public ApiResult<?> getAllByCompanyId(Long companyId) {
        return null;
    }

    @Override
    public ApiResult<?> delete(Long clientId) {
        return null;
    }

    @Override
    public List<ClientResDto> getAll() {
        return null;
    }

    @Override
    public void checkingClientByIdListOrElseThrow(List<Long> clientIdList) {

        // TODO: 06/11/22

    }


//-----------------------------HELPER METHOD-------------------------------------
    private void checkingClientByPhoneNumberOrElseThrow(String phoneNumber) {
        if (clientRepository.existsByPhoneNumber(phoneNumber))
            throw RestException.restThrow("Bu raqamli mijoz bazada mavjud", HttpStatus.CONFLICT);
    }

    private void saveClient(Client client) {
        try {
            clientRepository.save(client);
        }catch (Exception e){
            e.printStackTrace();
            throw RestException.restThrow("Client not saved");
        }
    }

    private void updateClient(Client client, ClientReqDto clientReqDto) {

        if (clientRepository.existsByPhoneNumberAndIdNot(clientReqDto.getPhoneNumber(),client.getId()))
            throw new RestException("Yangi kiritgan raqamiz bazada mavjudligi uchun boshqa raqam kiriting!",HttpStatus.CONFLICT);

        client.setFullName(clientReqDto.getFullName());
        client.setPhoneNumber(clientReqDto.getPhoneNumber());
    }

    private ClientResDto mapClient(Client client) {
        return new ClientResDto(client.getId(),client.getFullName(), client.getPhoneNumber());
    }

}
