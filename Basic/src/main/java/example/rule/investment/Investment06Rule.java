package example.rule.investment;

import example.rule.domain.User;
import example.rule.domain.type.AccountType;

public class Investment06Rule extends Rule {

    public static final Integer VALUE = 10;

    @Override
    public Boolean check(User user) {
        return user.getAccount().getAccountType().equals(AccountType.PASSIVE);
    }

    @Override
    public Integer investmentPercentage() {
        return VALUE;
    }
}