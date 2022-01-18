package example.rule.investment;

import example.rule.domain.User;
import example.rule.domain.type.AccountType;

import java.math.BigDecimal;

public class Investment03Rule extends Rule {

    public static final Integer VALUE = 75;

    @Override
    public Boolean check(User user) {
        BigDecimal value =  new BigDecimal(90000);
        return user.getAge() >= 30 && user.getAccount().getAccountType().equals(AccountType.AGGRESSIVE) && user.getAccount().getValue().compareTo(value) > 0 ;
    }

    @Override
    public Integer investmentPercentage() {
        return VALUE;
    }
}