package me.vister.timesorting.utils;

import java.util.HashSet;
import java.util.Set;

public class NumbersToSort {
    private final Set<Integer> numbers;

    public NumbersToSort() {
        numbers = new HashSet<>();
    }

    public Set<Integer> getNumbers() {
        return numbers;
    }

    public boolean addNumber(int number) {
        return numbers.add(number);
    }

    public boolean removeNumber(int number) {
        return numbers.remove(number);
    }
}