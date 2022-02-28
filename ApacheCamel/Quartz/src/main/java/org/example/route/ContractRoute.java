package org.example.route;


import org.springframework.stereotype.Component;


@Component
public class ContractRoute extends AbstractRoute {

    @Override
    public void configure() throws Exception {
        fromQuartzCron("ContractRoute", "cron.contract-update")
                .routeId("ContractRoute")
                .log("Starting Select")
                .to("sql:classpath:sql/contract/select.sql")
                .split().body()
                .log("Starting Update")
                .to("sql:classpath:sql/contract/update.sql")
                .log("Finished");

    }
}
