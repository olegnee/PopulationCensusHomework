package com.company;
import java.util.*;
import java.util.stream.*;


public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long minorPopulation = persons.stream().
                filter(x -> x.getAge() < 18).
                count();

        List<String> recruitablePopulation = persons.stream().
                filter(x -> x.getAge() >= 18).
                filter(x -> x.getAge() <= 27).
                map(x -> x.getFamily()).
                collect(Collectors.toList());

        List<Person> workablePopulation = persons.stream().
                filter(x -> x.getEducation() == Education.HIGHER).
                filter(x -> x.getSex() == Sex.WOMAN && x.getAge() >= 18).
                filter(x -> x.getSex() == Sex.WOMAN && x.getAge() <= 60).
                filter(x -> x.getSex() == Sex.MAN && x.getAge() >= 18).
                filter(x -> x.getSex() == Sex.MAN && x.getAge() <= 65).
                sorted(Comparator.comparing(x -> x.getFamily())).
                collect(Collectors.toList());
    }
}
