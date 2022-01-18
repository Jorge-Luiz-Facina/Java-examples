package example.rule.investment;

import example.rule.domain.User;
import example.rule.domain.type.AccountType;

public class Investment01Rule extends Rule {

    public static final Integer VALUE = 80;

    @Override
    public Boolean check(User user) {
        return user.getAge() > 18 && user.getAge() < 30 && user.getAccount().getAccountType().equals(AccountType.AGGRESSIVE);
    }

    @Override
    public Integer investmentPercentage() {
        return VALUE;
    }
}
