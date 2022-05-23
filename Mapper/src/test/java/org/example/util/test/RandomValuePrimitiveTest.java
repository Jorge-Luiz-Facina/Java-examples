package org.example.util.test;

import org.example.util.RandomValueUtil;
import org.example.util.ReflectionFieldUtil;
import org.example.util.model.Primitive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RandomValuePrimitiveTest {

    @Test
    public void primitiveValue_Ok_Test(){
        Primitive primitive = new Primitive();
        assertEquals(0, primitive.getIntValue());
        assertEquals(0, primitive.getShortValue());
        assertEquals(0, primitive.getByteValue());
        assertEquals(0, primitive.getLongValue());
        assertEquals('\u0000', primitive.getCharValue());
        assertEquals(0.0, primitive.getFloatValue());
        assertEquals(0.0, primitive.getDoubleValue());
        assertEquals(false, primitive.isBooleanValue());
    }

    @Test
    void primitiveValueNotEqualsRandoValueGenerate_Ok_Test(){
        Primitive primitive = new Primitive();
        Primitive primitiveRandom = RandomValueUtil.generate(Primitive.class);
        ReflectionFieldUtil.verifyFields(primitive);
        assertNotEquals(primitiveRandom.getIntValue(), primitive.getIntValue());
        assertNotEquals(primitiveRandom.getShortValue(), primitive.getShortValue());
        assertNotEquals(primitiveRandom.getByteValue(), primitive.getByteValue());
        assertNotEquals(primitiveRandom.getLongValue(), primitive.getLongValue());
        assertNotEquals(primitiveRandom.getCharValue(), primitive.getCharValue());
        assertNotEquals(primitiveRandom.getFloatValue(), primitive.getFloatValue());
        assertNotEquals(primitiveRandom.getDoubleValue(), primitive.getDoubleValue());
        assertNotEquals(primitiveRandom.isBooleanValue(), primitive.isBooleanValue());
    }
}
