package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.entity.Client;
import uz.ataboyev.warehouse.enums.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDtoForPageable {

    private Long id;

    private Type clientType;

    private String fullName;


    public static ClientDtoForPageable make(Client client) {
        return new ClientDtoForPageable(client.getId(),client.getClientType(), client.getFullName());

    }
}
