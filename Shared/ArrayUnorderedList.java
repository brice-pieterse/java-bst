package Shared;

import java.util.ArrayList;

// Author - Brice Pieterse
// Student id - T00700445
// helper class with several methods common to an unordered list
// (only one of these methods are used as a helper to our binary tree classes - addToRear)
public class ArrayUnorderedList<T> {

    public ArrayList<T> arr;

    public ArrayUnorderedList() {
        arr = new ArrayList<T>();
    }

    // adds element to the front of the list
    public void addToFront(T element) {
        arr.add(0, element);
    }

    // adds element to the rear of the list
    public void addToRear(T element) {
        arr.add(arr.size(), element);
    }

    // the next two methods adds an element before or after another particular element
    public void addAfter(T element, T target) {
        arr.add(arr.indexOf(target) + 1, element);
    }

    public void addBefore(T element, T target) {
        int location = arr.indexOf(target);

        if (location == 0) {
            addToFront(element);
        } else {
            arr.add(arr.indexOf(target) - 1, element);
        }
    }

}
