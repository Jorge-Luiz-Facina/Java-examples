package argumentcaptor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ArgumentCaptorTest {

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Mock
    private Notification notification;

    @InjectMocks
    private ActionUser actionUser;

    @Test
    public void captorUserValueNotificationSendTest() {
        String name = "Lucas";
        Integer age = 20;

        actionUser.addUser(name, age);
        Mockito.verify(notification).send(userCaptor.capture());
        User value = userCaptor.getValue();

        assertEquals(value.getName(), name);
        assertEquals(value.getAge(), age);
    }
}
