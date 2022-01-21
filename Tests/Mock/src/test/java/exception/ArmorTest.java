package exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArmorTest {

    @Mock
    private Armor armorMock;

    @Spy
    private Armor armorSpy;
    @Test
    public void testMethodReturnThenThrowGetElements() {
        when(armorMock.getElements())
                .thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> {
            armorMock.getElements();
        });
    }

    @Test
    public void testMethodVoidDoThrowAdd() {
        doThrow(NullPointerException.class)
                .when(armorMock)
                .add(anyString());

        assertThrows(NullPointerException.class, () -> {
            armorMock.add("Fire");
        });
    }

    @Test
    public void testMethodReturnThenThrowObjectExceptionGetElements() {
        String message = "Error";
        when(armorMock.getElements())
                .thenThrow(new NullPointerException(message));

        Exception exception = assertThrows(NullPointerException.class, () -> {
            armorMock.getElements();
        });

        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(message));
    }

    @Test
    public void testMethodVoidDoThrowObjectExceptionAdd() {
        String message = "Error";
        doThrow(new NullPointerException(message))
                .when(armorMock)
                .add(anyString());

        Exception exception = assertThrows(NullPointerException.class, () -> {
            armorMock.add("Fire");
        });

        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(message));
    }

    @Test
    public void testMethodReturnSpyThenThrowGetElements() {
        when(armorSpy.getElements())
                .thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> {
            armorSpy.getElements();
        });
    }
}
