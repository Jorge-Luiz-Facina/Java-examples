package example.rule.domain;


import example.rule.domain.type.AccountType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class Account {

    private UUID id;
    private AccountType accountType;
    private BigDecimal value;
}
