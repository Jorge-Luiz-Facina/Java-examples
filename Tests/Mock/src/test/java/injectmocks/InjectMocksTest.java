package injectmocks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class InjectMocksTest {

    @Mock
    private Processor processor;

    @InjectMocks
    private Computer computer;

    @Test
    public void injectMocksTest(){
        String processorName = "test";
        Mockito.when(processor.getName()).thenReturn(processorName);

        assertEquals(processorName, computer.getProcessorName());
    }
}
