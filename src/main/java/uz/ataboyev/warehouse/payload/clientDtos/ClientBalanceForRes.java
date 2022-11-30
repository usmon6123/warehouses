package uz.ataboyev.warehouse.payload.clientDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor@NoArgsConstructor@Data
public class ClientBalanceForRes {
    private Long clientIdLong;
    private String clientName;
    private Double balanceDollar;
    private boolean isDollarPositive;//summa no'ldan baland bo'lsa true aks holda false
    private Double balanceSum;
    private boolean isSumPositive;//summa no'ldan baland bo'lsa true aks holda false


    public static ClientBalanceForRes make(ClientBalance clientBalance) {
        return new ClientBalanceForRes(
                clientBalance.getClientIdLong(),
                clientBalance.getClientName(),
                Double.parseDouble(clientBalance.getBalanceDollar()),
                isPositive(clientBalance.getBalanceDollar()),
                Double.parseDouble(clientBalance.getBalanceSum()),
                isPositive(clientBalance.getBalanceSum())
        );
    }

    private static boolean isPositive(String summa) {
        double sum = Double.parseDouble(summa);
        return sum>=0;
    }
}
