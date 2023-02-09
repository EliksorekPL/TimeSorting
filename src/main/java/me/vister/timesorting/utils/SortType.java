package me.vister.timesorting.utils;

public enum SortType {
    BUBBLE_SORT ("Bubble sort"),
    SELECTION_SORT ("Selection sort"),
    INSERTION_SORT ("Insertion sort"),
    BINARY_SORT ("Binary sort");

    private final String text;

    SortType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}