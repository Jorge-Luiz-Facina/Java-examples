package org.example.context.event;

import lombok.Getter;
import org.example.context.dto.EmailSendDTO;
import org.springframework.context.ApplicationEvent;

@Getter
public class EmailEvent extends ApplicationEvent {

    private EmailSendDTO emailSend;

    public EmailEvent(Object source, EmailSendDTO emailSend) {
        super(source);
        this.emailSend = emailSend;
    }
}
