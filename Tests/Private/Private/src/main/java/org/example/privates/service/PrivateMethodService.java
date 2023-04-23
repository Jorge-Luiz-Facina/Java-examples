package org.example.privates.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivateMethodService {

    private Integer sum(Integer valueOne, Integer valueTwo) {
        return valueOne + valueTwo;
    }

    private Integer sumList(List<Integer> valueList) {
        return valueList.stream().mapToInt(Integer::intValue).sum();
    }

    private Object sumObject(List<Object> objectList) {
        return objectList.stream().mapToInt(value -> (Integer)value).sum();
    }
}
