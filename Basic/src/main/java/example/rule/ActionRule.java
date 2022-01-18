package example.rule;

import example.rule.domain.User;
import example.rule.investment.*;

import java.util.Arrays;
import java.util.List;

public class ActionRule {
    public static final Integer DEFAULT_INVESTMENT = 20;

    public Integer checkInvestment(User user){

        Integer value = DEFAULT_INVESTMENT;
        List<Rule> investmentRules = Arrays.asList(new Investment01Rule(), new Investment02Rule(),
                new Investment03Rule(),new Investment04Rule(),new Investment05Rule(),new Investment06Rule());

        for(Rule rule : investmentRules) {
            if(rule.check(user)) {
                return rule.investmentPercentage();
            }
        }
        return value;
    }

}
