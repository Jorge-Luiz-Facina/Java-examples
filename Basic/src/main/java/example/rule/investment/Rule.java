package example.rule.investment;

import example.rule.domain.User;

public abstract class Rule {

    public abstract Boolean check(User user);

    public abstract Integer investmentPercentage();

}
