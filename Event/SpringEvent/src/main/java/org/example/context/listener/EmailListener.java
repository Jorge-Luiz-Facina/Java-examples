package org.example.context.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.component.EmailComponent;
import org.example.context.event.EmailEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;


@Component
@RequiredArgsConstructor
public class EmailListener implements ApplicationListener<EmailEvent> {

    private final EmailComponent emailComponent;

    @Override
    public void onApplicationEvent(EmailEvent event) {
        CompletableFuture.runAsync(() -> {
            try {
                emailComponent.sendEmail(event.getEmailSend());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
