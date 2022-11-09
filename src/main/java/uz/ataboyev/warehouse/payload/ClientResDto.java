package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.entity.Client;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResDto {

    private Long id;

    private String fullName;

    private String phoneNumber;

    public static ClientResDto make(Client client) {
        return new ClientResDto(client.getId(), client.getFullName(), client.getPhoneNumber());

    }
}
