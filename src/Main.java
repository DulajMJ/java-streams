import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {


    private static List<Person> getPeople(){

        List<Person> personList=new ArrayList<>();
                Person person1= new Person("Antonio", 20, Gender.MALE);
                Person person2= new Person("Alina Smith", 33, Gender.FEMALE);
                Person person3= new Person("Helen White", 57, Gender.FEMALE);
                Person person4= new Person("Alex Boz", 14, Gender.MALE);
                Person person5= new Person("Jamie Goa", 99, Gender.MALE);
                Person person6= new Person("Anna Cook", 7, Gender.FEMALE);
                Person person7= new Person("Zelda Brown", 120, Gender.FEMALE);

                personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
        personList.add(person5);
        personList.add(person6);
        personList.add(person7);
                return personList;
    }
    public static void main(String[] args) {
        //Filter
        List<Person> filterPerson=getPeople().stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .collect(Collectors.toList());
        System.out.println("FILTER BY GENDER");
        filterPerson.forEach(System.out::println);

        //Sort ASC
        List<Person> sortPersonASC=getPeople().stream()
                .sorted(Comparator.comparing(Person::getAge).reversed())
                .collect(Collectors.toList());
        System.out.println("SORT BY AGE-ASC");
        sortPersonASC.forEach(System.out::println);

        //Sort DESC
        List<Person> sortPersonDESC=getPeople().stream()
                .sorted(Comparator.comparing(Person::getAge).reversed())
                .collect(Collectors.toList());
        System.out.println("SORT BY AGE-DESC");
        sortPersonASC.forEach(System.out::println);

        //All match
        boolean allMatchPerson=getPeople().stream().
                allMatch(person -> person.getAge()>8);
        System.out.println("ALL MATCH");
        System.out.println(allMatchPerson);

        //Any match
        boolean anyMatch=getPeople().stream()
                .anyMatch(person -> person.getAge()>100);
        System.out.println("ANY MATCH");
        System.out.println(anyMatch);

        //Non match
        boolean nonMatch=getPeople().stream()
                .noneMatch(person -> person.getName().equals("Antonio"));
        System.out.println("NON MATCH");
        System.out.println(nonMatch);

        //MAX
        getPeople().stream()
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(person -> {
                    System.out.println("MAX AGE");
                    System.out.println(person.getName());
                });

        //MIN
        getPeople().stream()
                .min(Comparator.comparing(Person::getAge))
                .ifPresent(person -> {
                    System.out.println("MIN AGE");
                    System.out.println(person.getName());
                });

        //GROUP
        Map<Gender,List<Person>> genderListGroup=getPeople().stream()
                .collect(Collectors.groupingBy(Person::getGender));
        genderListGroup.forEach((gender,person)->{
            System.out.println(gender);
            person.forEach(System.out::println);
        });


        //OLD female
        Optional<String> oldfemale=getPeople().stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName);
        oldfemale.ifPresent(System.out::println);

    }
}
