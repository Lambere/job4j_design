package ru.job4j.generic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        Generics generics = new Generics();
        List<Predator> first = new ArrayList<>();
        List<Predator> second = new ArrayList<>();
        List<Tiger> third = new ArrayList<>();
        first.add(new Predator());
        second.add(new Predator());
        third.add(new Tiger());

        generics.printObject(first);
        generics.printObject(second);
        generics.printObject(third);
        System.out.println();

        generics.printBoundedWildCard(first);
        generics.printBoundedWildCard(second);
        generics.printBoundedWildCard(third);
        System.out.println();

        generics.printLowerBoundedWildCard(first);
        generics.printLowerBoundedWildCard(second);
    }

    public void printObject(List<?> list) {
        for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
            Object next = iterator.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printBoundedWildCard(List<? extends Predator> list) {
        for (Iterator<? extends Animal> iterator = list.iterator(); iterator.hasNext();) {
            Object next = iterator.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printLowerBoundedWildCard(List<? super Predator> list) {
        for (Iterator<? super Predator> iterator = list.iterator(); iterator.hasNext();) {
            Object next = iterator.next();
            System.out.println("Текущий элемент: " + next);
        }
    }
}
