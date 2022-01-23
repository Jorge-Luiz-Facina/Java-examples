package spy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SpyTest {

    @Spy
    private HashMap<String, Object> hashMap = new HashMap<String, Object>();

    @Test
    public void spyTest() {
        String key = "test";
        Object object = new Object();

        hashMap.put(key, object);
        Mockito.verify(hashMap).put(key, object);

        Mockito.doReturn(20).when(hashMap).size();

        assertEquals(20, hashMap.size());
    }

}
