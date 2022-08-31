package org.example.component;

import lombok.extern.log4j.Log4j2;
import org.example.context.dto.EmailSendDTO;
import org.springframework.stereotype.Component;


@Log4j2
@Component
public class EmailComponent {

    public void sendEmail(EmailSendDTO emailSend) {
        try {
            log.info("initialized sending email");
            Thread.sleep(10000);
            log.info("finished sending email");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
