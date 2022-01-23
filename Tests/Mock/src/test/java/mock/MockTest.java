package mock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class MockTest {

    @Mock
    private HashMap<String, Object> hashMap = new HashMap<String, Object>();

    @Test
    public void mockTest() {
        String key = "test";
        Object object = new Object();

        hashMap.put(key, object);
        Mockito.verify(hashMap).put(key, object);

        Mockito.when(hashMap.size()).thenReturn(20);

        assertEquals(20, hashMap.size());
    }
}
