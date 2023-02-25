package me.vister.timesorting.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TimeSort {
    private final Map<SortType, Float> sortMethodResult;
    private final int[] defaultArray;

    public TimeSort(NumbersToSort numbers) {
        defaultArray = numbers.getNumbers().stream().mapToInt(Integer::intValue).toArray();
        sortMethodResult = new HashMap<>();
    }

    public String getSortTime(SortType sortType) {
        return sortMethodResult.get(sortType) != null ? sortMethodResult.get(sortType) + " ms" : "--";
    }

    public String getLowestSortTime() {
        return !sortMethodResult.isEmpty() ? Collections.min(sortMethodResult.values()) + " ms" : "--";
    }

    public String getLowestSortTimeMethod() {
        for (Map.Entry<SortType, Float> entry : sortMethodResult.entrySet())
            if (Objects.equals(entry.getValue(), Collections.min(sortMethodResult.values())))
                return entry.getKey().toString();
        return "--";
    }

    private int[] getDefaultArray() {
        return defaultArray;
    }

    private void swap(int[] numbers, int index1, int index2) {
        int temp = numbers[index1];
        numbers[index1] = numbers[index2];
        numbers[index2] = temp;
    }

    public void bubbleSort() {
        int[] numbers = getDefaultArray();
        long startTime = System.nanoTime();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 1; j < (numbers.length - i); j++)
                if (numbers[j - 1] > numbers[j]) {
                    swap(numbers, (j - 1), j);
                }
        }
        long endTime = System.nanoTime();
        sortMethodResult.put(SortType.BUBBLE_SORT, ((endTime - startTime) / 1_000_000.0F));
    }

    public void selectionSort() {
        int[] numbers = getDefaultArray();
        long startTime = System.nanoTime();
        for (int i = 0; i < numbers.length - 1; i++) {
            int minimum_index = i;
            for (int j = i + 1; j < numbers.length; j++)
                if (numbers[j] < numbers[minimum_index]) minimum_index = j;
            swap(numbers, minimum_index, i);
        }
        long endTime = System.nanoTime();
        sortMethodResult.put(SortType.SELECTION_SORT, ((endTime - startTime) / 1_000_000.0F));
    }

    public void insertionSort() {
        int[] numbers = getDefaultArray();
        long startTime = System.nanoTime();
        for (int i = 0; i < numbers.length; i++) {
            int key = numbers[i];
            int j = i - 1;
            while (j >= 0 && numbers[j] > key) {
                numbers[j+1] = numbers[j];
                j = j - 1;
            } numbers[j + 1] = key;
        }
        long endTime = System.nanoTime();
        sortMethodResult.put(SortType.INSERTION_SORT, ((endTime - startTime) / 1_000_000.0F));
    }

    public void binarySort() {
        int[] numbers = getDefaultArray();
        boolean swapped = true;
        int start = 0;
        int end = numbers.length;
        long startTime = System.nanoTime();
        while (swapped) {
            swapped = false;
            for (int i = start; i < end; i++) {
                for (int j = 1; j < (numbers.length - i); j++)
                    if (numbers[j - 1] > numbers[j]) {
                        swap(numbers, (j - 1), j);
                        swapped = true;
                    }
            } if (!swapped) break;
            swapped = false;
            end--;
            for (int i = end - 1; i >= start; i--) {
                if (numbers[i] >= numbers[i+1]) {
                    swap(numbers, i, (i + 1));
                    swapped = true;
                }
            } start++;
        }
        long endTime = System.nanoTime();
        sortMethodResult.put(SortType.BINARY_SORT, ((endTime - startTime) / 1_000_000.0F));
    }
}
