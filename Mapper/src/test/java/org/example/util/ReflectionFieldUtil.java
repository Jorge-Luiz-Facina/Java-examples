package org.example.util;

import lombok.SneakyThrows;
import org.example.util.model.Primitive;
import org.example.util.model.SelectField;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public abstract class ReflectionFieldUtil {

    @SneakyThrows
    public static void verifyFields(Object object) {
        verifyFields(object, object.getClass(), Arrays.asList());
    }

    @SneakyThrows
    public static void verifyFields(Object object, List<SelectField> selectFields) {
        verifyFields(object, object.getClass(), selectFields);
    }

    @SneakyThrows
    public static void verifyFields(Object object, Class typeClass, List<SelectField> selectFields) {

        if (object != null) {
            verifyFieldsGeneral(object, typeClass, selectFields);
            while (!typeClass.getSuperclass().getSimpleName().equals("Object")) {
                typeClass = typeClass.getSuperclass();
                verifyFieldsGeneral(object, typeClass, selectFields);
            }
        } else {
            throw new InternalError("The object cannot be null");
        }
    }

    private static void verifyFieldsGeneral(Object object, Class typeClass, List<SelectField> selectFields) throws Exception {
        String className = typeClass.getSimpleName();
        SelectField selectedFieldsInObject = getSelectedFieldsInObject(selectFields, className);
        Field[] fields = typeClass.getDeclaredFields();
        errorExistFieldSelected(selectedFieldsInObject, fields);
        AccessibleObject.setAccessible(fields, true);
        for (int i = 0; i < fields.length; ++i) {
            Field field = fields[i];
            if (ruleIncludeOrExclude(selectedFieldsInObject, field)) {

                try {
                    if ((field.getType().isPrimitive() && !isPrimitiveNonNull(field, object)) || Objects.isNull(field.get(object))) {
                        throw new Exception("This field cannot be null: " + className + "." + field.getName());
                    }
                    objectVerifyFieldsNonNull(object, field, selectFields);
                    collectionVerifyFieldsNonNull(object, field, selectFields);
                    collectionObjectVerifyFieldsNonNull(object, selectFields);
                } catch (IllegalAccessException exception) {
                    throw new InternalError("Unexpected IllegalAccessException");
                }
            } else {

                if ((field.getType().isPrimitive() && isPrimitiveNonNull(field, object)) || Objects.nonNull(field.get(object))) {
                    throw new Exception("This field cannot be (not null): " + className + "." + field.getName());
                }
            }
        }
    }

    private static Boolean ruleIncludeOrExclude(SelectField selectField, Field field) {
        if (Objects.isNull(selectField)) {
            return true;
        }
        String fieldName = field.getName();

        if (selectField.getIsInclude()) {
            return selectField.getFields().contains(fieldName);
        } else {
            return (!selectField.getFields().contains(fieldName));
        }
    }

    private static void collectionVerifyFieldsNonNull(Object object, Field field, List<SelectField> selectedFieldsInObject) throws IllegalAccessException {
        if (field.get(object) instanceof Collection) {
            for (Object objectList : ((Collection) field.get(object))) {
                verifyFields(objectList, objectList.getClass(), selectedFieldsInObject);
            }
        }
    }

    private static void collectionObjectVerifyFieldsNonNull(Object object, List<SelectField> selectedFieldsInObject) throws IllegalAccessException {
        if (object instanceof Collection) {
            for (Object objectList : ((Collection) object)) {
                verifyFields(objectList, objectList.getClass(), selectedFieldsInObject);
            }
        }
    }

    private static void objectVerifyFieldsNonNull(Object object, Field field, List<SelectField> selectedFieldsInObject) throws IllegalAccessException {
        if (!field.get(object).getClass().getPackageName().contains("java") && !field.get(object).getClass().getSuperclass().getSimpleName().equals("Enum")) {
            verifyFields(field.get(object), field.get(object).getClass(), selectedFieldsInObject);
        }
    }

    private static SelectField getSelectedFieldsInObject(List<SelectField> selectFields, String className) {
        if (!selectFields.isEmpty()) {
            return selectFields.stream().filter(field -> field.getTypeClass().getSimpleName().equals(className)).
                    collect(Collectors.toList()).stream().findAny().orElse(null);
        }
        return null;
    }

    private static void errorExistFieldSelected(SelectField selectField, Field[] fields) throws Exception {
        List<String> selectedFieldsInObject = new ArrayList<>();
        if (Objects.isNull(selectField) || selectField.getFields().isEmpty()) {
            return;
        } else {
            selectedFieldsInObject.addAll(selectField.getFields());
        }

        List<String> fieldsString = Arrays.stream(fields).collect(Collectors.toList()).stream().
                map(field -> field.getName()).collect(Collectors.toList());
        if (!selectedFieldsInObject.isEmpty()) {
            selectedFieldsInObject.removeAll(fieldsString);
            if (!selectedFieldsInObject.isEmpty()) {
                throw new Exception("There is some field chosen that is not inside the object: " + selectField.getTypeClass().getSimpleName() + selectedFieldsInObject);
            }
        }
    }

    private static boolean isPrimitiveNonNull(Field field, Object object) throws IllegalAccessException {

        Primitive primitive = new Primitive();
        switch (field.getType().getSimpleName()) {
            case "int":
                if (field.get(object).equals(primitive.getIntValue()))
                    return false;
                break;
            case "short":
                if (field.get(object).equals(primitive.getShortValue()))
                    return false;
                break;
            case "byte":
                if (field.get(object).equals(primitive.getByteValue()))
                    return false;
                break;
            case "long":
                if (field.get(object).equals(primitive.getLongValue()))
                    return false;
                break;
            case "char":
                if (field.get(object).equals(primitive.getCharValue()))
                    return false;
                break;
            case "float":
                if (field.get(object).equals(primitive.getFloatValue()))
                    return false;
                break;
            case "double":
                if (field.get(object).equals(primitive.getDoubleValue()))
                    return false;
                break;
            case "boolean":
                if (field.get(object).equals(primitive.isBooleanValue()))
                    return false;
                break;
        }
        return true;
    }
}
