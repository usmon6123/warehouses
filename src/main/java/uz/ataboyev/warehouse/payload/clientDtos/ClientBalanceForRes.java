package uz.ataboyev.warehouse.payload.clientDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor@NoArgsConstructor@Data
public class ClientBalanceForRes {
    private Long clientIdLong;
    private String clientName;
    private String balanceDollar;
    private String balanceSum;

}
