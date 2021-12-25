package example;

import java.lang.reflect.Field;

public class GetNameFieldsAndValue {

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
        Test test =  new Test();
        test.field1 = "J";
        test.field2 = "F";
        for(Field field : test.getClass().getFields()) {
            System.out.println(field.getGenericType() +" "+field.getName() + " = " + field.get(test));
        }
    }

    public static class Test{
        public String field1;
        public String field2;
    }
}
