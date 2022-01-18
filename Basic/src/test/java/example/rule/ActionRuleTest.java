package example.rule;

import example.rule.domain.Account;
import example.rule.domain.User;
import example.rule.domain.type.AccountType;
import example.rule.investment.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.UUID;

public class ActionRuleTest {

    @Test
    public void investment01RuleTest(){
        User user = getDefaultUser();
        user.getAccount().setAccountType(AccountType.AGGRESSIVE);
        investmentRuleGeneric(Investment01Rule.VALUE, user);
    }

    @Test
    public void investment02RuleTest(){
        User user = getDefaultUser();
        user.getAccount().setAccountType(AccountType.AGGRESSIVE);
        user.getAccount().setValue(new BigDecimal(2000));
        user.setAge(40);
        investmentRuleGeneric(Investment02Rule.VALUE, user);
    }

    @Test
    public void investment03RuleTest(){
        User user = getDefaultUser();
        user.getAccount().setAccountType(AccountType.AGGRESSIVE);
        user.getAccount().setValue(new BigDecimal(9990000));
        user.setAge(31);
        investmentRuleGeneric(Investment03Rule.VALUE, user);
    }

    @Test
    public void investment04RuleTest(){
        User user = getDefaultUser();
        user.getAccount().setAccountType(AccountType.NORMAL);
        investmentRuleGeneric(Investment04Rule.VALUE, user);
    }

    @Test
    public void investment05RuleTest(){
        User user = getDefaultUser();
        user.getAccount().setAccountType(AccountType.NORMAL);
        user.getAccount().setValue(new BigDecimal(50000));
        user.setAge(31);
        investmentRuleGeneric(Investment05Rule.VALUE, user);
    }

    @Test
    public void investment06RuleTest(){
        User user = getDefaultUser();
        user.getAccount().setAccountType(AccountType.PASSIVE);
        investmentRuleGeneric(Investment06Rule.VALUE, user);
    }

    @Test
    public void investmentDefaultTest(){
        User user = getDefaultUser();
        user.getAccount().setAccountType(AccountType.AGGRESSIVE);
        user.getAccount().setValue(new BigDecimal(5000));
        user.setAge(51);
        investmentRuleGeneric(ActionRule.DEFAULT_INVESTMENT, user);
    }

    private void investmentRuleGeneric(Integer valueExpected, User user){
        ActionRule actionRule =  new ActionRule();
        Integer value = actionRule.checkInvestment(user);

        Assert.assertEquals(valueExpected, value);
    }

    private User getDefaultUser(){
        return User.builder().
                name("Jorge Luiz Facina").
                account(
                        Account.builder().
                                id(UUID.randomUUID()).
                                accountType(AccountType.NORMAL).
                                value(new BigDecimal(1000)).
                                build()).
                age(20).
                email("example@example.com").
                cpf("08428752574").
                build();
    }
}
