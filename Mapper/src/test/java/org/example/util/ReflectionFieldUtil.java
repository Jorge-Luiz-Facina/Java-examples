package org.example.util;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.*;

public abstract class ReflectionFieldUtil {

    public static boolean verifyFieldsNonNull(Object object) {
        return verifyFieldsNonNull(object, Arrays.asList());
    }

    public static boolean verifyFieldsNonNull(Object object, List<String> excludeFields) {
        if (object != null) {
            Class typeClass = object.getClass();
            return verifyFieldsNonNull(object, typeClass, excludeFields);
        } else {
            return false;
        }
    }

    private static Boolean verifyFieldsNonNull(Object object, Class clazz, List<String> excludeFields) {
        Field[] fields = clazz.getDeclaredFields();
        AccessibleObject.setAccessible(fields, true);
        for (int i = 0; i < fields.length; ++i) {
            Field field = fields[i];
            if (!excludeFields.contains(object.getClass().getSimpleName() + "." + field.getName())) {
                try {
                    if (Objects.isNull(field.get(object))) {
                        return false;
                    }
                    if (!objectVerifyFieldsNonNull(object, field, excludeFields)) {
                        return false;
                    }
                    if (!collectionVerifyFieldsNonNull(object, field, excludeFields)) {
                        return false;
                    }
                } catch (IllegalAccessException exception) {
                    throw new InternalError("Unexpected IllegalAccessException");
                }
            }
        }
        return true;
    }

    private static Boolean collectionVerifyFieldsNonNull(Object object, Field field, List<String> excludeFields) throws IllegalAccessException {
        if (field.get(object) instanceof Collection) {
            for (Object objectList : ((Collection) field.get(object))) {
                if (!verifyFieldsNonNull(objectList, excludeFields)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static Boolean objectVerifyFieldsNonNull(Object object, Field field, List<String> excludeFields) throws IllegalAccessException {
        if (!field.get(object).getClass().getPackageName().contains("java")) {
            if (!verifyFieldsNonNull(field.get(object), excludeFields)) {
                return false;
            }
        }
        return true;
    }
}
