package org.example.util;

import org.example.util.model.RandomValueExcludeField;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import java.util.Arrays;
import java.util.List;

public abstract class RandomValueUtil {

    public static <D> D generate(Class<D> outCLass) {
        return generate(outCLass, Arrays.asList());
    }

    public static <D> D generate(Class<D> outCLass, List<RandomValueExcludeField> excludeFields) {
        EasyRandomParameters parameters = new EasyRandomParameters();
        parameters.stringLengthRange(5, 10);
        parameters.collectionSizeRange(2, 2);
        if (!excludeFields.isEmpty()) {
            excludeFields_(parameters, excludeFields);
        }
        EasyRandom generator = new EasyRandom(parameters);
        return generator.nextObject(outCLass);
    }

    private static void excludeFields_(EasyRandomParameters parameters, List<RandomValueExcludeField> excludeFields) {
        excludeFields.forEach(randomValueExcludeField ->
        {
            parameters.excludeField(FieldPredicates.named(randomValueExcludeField.getField()).and(FieldPredicates.inClass(randomValueExcludeField.getOutCLass())));
        });
    }

}


