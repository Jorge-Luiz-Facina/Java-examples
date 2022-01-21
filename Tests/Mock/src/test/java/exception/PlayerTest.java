package exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PlayerTest {

    @Test
    public void testMethodReturnTryCatchNullPointerThenThrowObjectExceptionGetElements() {
        Player player = new Player();

        Armor armorMock = mock(Armor.class);
        when(armorMock.getElements())
                .thenThrow(NullPointerException.class);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            player.getElements();
        });

        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains("Null Pointer armor..."));
    }

}
