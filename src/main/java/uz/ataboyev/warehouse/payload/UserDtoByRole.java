package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.entity.User;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDtoByRole {
    private UUID id;
    private String username;

    public UserDtoByRole(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
