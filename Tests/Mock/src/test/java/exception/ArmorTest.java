package exception;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ArmorTest {

    @Test
    public void testMethodReturnThenThrowGetElements() {
        Armor armorMock = mock(Armor.class);
        when(armorMock.getElements())
                .thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> {
            armorMock.getElements();
        });
    }

    @Test
    public void testMethodVoidDoThrowAdd() {
        Armor armorMock = mock(Armor.class);
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
        Armor armorMock = mock(Armor.class);
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
        Armor armorMock = mock(Armor.class);
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
        Armor armor = new Armor();
        Armor spy = Mockito.spy(armor);
        when(spy.getElements())
                .thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> {
            spy.getElements();
        });
    }
}
