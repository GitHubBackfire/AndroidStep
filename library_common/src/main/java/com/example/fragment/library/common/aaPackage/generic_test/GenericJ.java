package com.example.fragment.library.common.aaPackage.generic_test;

import java.util.ArrayList;
import java.util.List;

public class GenericJ {
    public static void main(String[] args) {
        List<Number> numberList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        copyAll(numberList, integerList);
        copyAll2(numberList, integerList);


    }

    private static <T> void copyAll(List<T> to, List<? extends T> from) {
        to.addAll(from);
    }


    private static <T> void copyAll2(List<? super T> to, List<T> from) {
        to.addAll(from);
    }

    public abstract class BaseItem<T extends BaseItem> {
    }

    public class CommonItem<T extends BaseItem> extends BaseItem<T> {
    }
}
