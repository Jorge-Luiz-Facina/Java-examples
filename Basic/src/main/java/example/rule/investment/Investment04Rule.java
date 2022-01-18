package example.rule.investment;

import example.rule.domain.User;
import example.rule.domain.type.AccountType;

import java.math.BigDecimal;

public class Investment04Rule extends Rule {

    public static final Integer VALUE = 50;

    @Override
    public Boolean check(User user) {
        BigDecimal value =  new BigDecimal(50000);
        return user.getAge() > 18 && user.getAge() < 30 && user.getAccount().getAccountType().equals(AccountType.NORMAL)
                && user.getAccount().getValue().compareTo(value) < 0;
    }

    @Override
    public Integer investmentPercentage() {
        return VALUE;
    }
}
