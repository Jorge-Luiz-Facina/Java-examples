package org.example.util;

import org.example.util.model.SelectExcludedField;
import org.example.util.model.SelectField;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.jeasy.random.api.Randomizer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class RandomValueUtil {

    public static <D> D generate(Class<D> outCLass) {
        return generate(outCLass, Arrays.asList());
    }

    public static <D> List<D> generateList(Class<D> outCLass) {
        return generateList(outCLass, Arrays.asList() , 1);
    }

    public static <D> List<D> generateList(Class<D> outCLass, int size) {
        return generateList(outCLass, Arrays.asList(), size);
    }

    public static <D> D generate(Class<D> outCLass, List<SelectExcludedField> excludeFields) {
        EasyRandomParameters parameters = getParameters(excludeFields);
        if (!excludeFields.isEmpty()) {
            executeExcludeFields(parameters, excludeFields);
        }
        EasyRandom generator = new EasyRandom(parameters);
        return generator.nextObject(outCLass);
    }

    public static <D> List<D> generateList(Class<D> outCLass, List<SelectExcludedField> excludeFields, int size) {
        EasyRandomParameters parameters = getParameters(excludeFields);
        EasyRandom generator = new EasyRandom(parameters);

        return generator.objects(outCLass, size)
                .collect(Collectors.toList());
    }

    private static EasyRandomParameters getParameters(List<SelectExcludedField> excludeFields) {
        EasyRandomParameters parameters = new EasyRandomParameters();
        parameters.stringLengthRange(1, 1);
        parameters.collectionSizeRange(2, 2);
        EasyRandomParametersPrimitive(parameters);
        if (!excludeFields.isEmpty()) {
            executeExcludeFields(parameters, excludeFields);
        }
        return parameters;
    }

    private static void EasyRandomParametersPrimitive(EasyRandomParameters parameters) {
        parameters.randomize(int.class, new Randomizer<Integer>() {
            @Override
            public Integer getRandomValue() {
                return 99;
            }
        });
        parameters.randomize(boolean.class, new Randomizer<Boolean>() {
            @Override
            public Boolean getRandomValue() {
                return true;
            }
        });
        parameters.randomize(short.class, new Randomizer<Short>() {
            @Override
            public Short getRandomValue() {
                return 99;
            }
        });
        parameters.randomize(byte.class, new Randomizer<Byte>() {
            @Override
            public Byte getRandomValue() {
                return 99;
            }
        });
        parameters.randomize(long.class, new Randomizer<Long>() {
            @Override
            public Long getRandomValue() {
                return 99L;
            }
        });
        parameters.randomize(char.class, new Randomizer<Character>() {
            @Override
            public Character getRandomValue() {
                return '9';
            }
        });
        parameters.randomize(float.class, new Randomizer<Float>() {
            @Override
            public Float getRandomValue() {
                return 9.9f;
            }
        });
        parameters.randomize(double.class, new Randomizer<Double>() {
            @Override
            public Double getRandomValue() {
                return 9.9;
            }
        });
    }

    private static void executeExcludeFields(EasyRandomParameters parameters, List<SelectExcludedField> excludeFields) {
        excludeFields.forEach(objectFields ->
        {
            objectFields.getFields().forEach(field -> {
                parameters.excludeField(
                        FieldPredicates.named(field).and(
                                FieldPredicates.inClass(objectFields.getTypeClass())));
            } );
        });
    }
}


