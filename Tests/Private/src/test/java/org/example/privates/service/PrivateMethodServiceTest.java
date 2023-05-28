package org.example.privates.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class PrivateMethodServiceTest {

    @InjectMocks
    PrivateMethodService privateMethodService;

    @Test
    public void sum_ok_Test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        assertEquals(2, (Integer) sum().invoke(privateMethodService, 1, 1));
    }

    @Test
    public void sumList_ok_Test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        assertEquals(2, (Integer) sumList().invoke(privateMethodService, Arrays.asList(1, 1)));
    }

    @Test
    public void sumObject_ok_Test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Object> objectList = List.of(1, 1);
        assertEquals(2, (Integer) sumObject().invoke(privateMethodService, objectList));
    }

    private Method sum() throws NoSuchMethodException {
        Method method = privateMethodService.getClass().getDeclaredMethod("sum", Integer.class, Integer.class);
        method.setAccessible(true);
        return method;
    }

    private Method sumList() throws NoSuchMethodException {
        Method method = privateMethodService.getClass().getDeclaredMethod("sumList", List.class);
        method.setAccessible(true);
        return method;
    }

    private Method sumObject() throws NoSuchMethodException {
        Method method = privateMethodService.getClass().getDeclaredMethod("sumObject", List.class);
        method.setAccessible(true);
        return method;
    }

}
