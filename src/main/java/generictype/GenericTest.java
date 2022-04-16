package generictype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericTest {

    static class Person {
        private String name;

        Person(String name) {
            this.name = name;
        }
    }

    static class Man extends Person {
        private int age;

        Man(String name) {
            super(name);
        }
    }

    static class Woman extends Person {
        private int height;

        Woman(String name) {
            super(name);
        }
    }

    /**
     * Can NOT use List<Object>, can only raw use of List.
     * Raw use of List means the generic type is not determined yet, can be determined later.
     */
    public static void createExtendsListViaList() {
        List persons = new ArrayList<>();
        Man man = new Man("zaniu");
        Woman woman = new Woman("lvting");
        persons.add(man);
        persons.add(woman);
        List<? extends Person> personList = persons;
        for (int i = 0; i < personList.size(); i++) {
            Person person = personList.get(i);
            System.out.println(person.name);
        }
    }

    /**
     * List<Object> means the generic type is determined, can NOT change it to undetermined type ?.
     */
    public static void createExtendsListViaListNotRawUseOfList() {
        List<Object> persons = new ArrayList<>();
        Man man = new Man("zaniu");
        Woman woman = new Woman("lvting");
        persons.add(man);
        persons.add(woman);
        List<Object> personList = persons;
        for (int i = 0; i < personList.size(); i++) {
            Person person = (Person) personList.get(i);
            System.out.println(person.name);
        }
    }

    /**
     * List<Person> is not super type of List<Man>,
     * but Array[Person] is super type of Array[Man].
     */
    public static void createExtendsListViaArray() {
        Person[] personArray = new Person[2];
        personArray[0] = new Man("zaniu");
        personArray[1] = new Woman("lvting");
        List<? extends Person> personArrayList = Arrays.asList(personArray);
        for (int i = 0; i < personArrayList.size(); i++) {
            Person person = personArrayList.get(i);
            System.out.println(person.name);
        }
    }

    /**
     * ? super Person can add elements, but when retrieving, only Object type is supported.
     *
     * @param persons
     */
    public static void retrieveObjectsFromList(List<? extends Person> persons) {
        List<? super Person> objects = new ArrayList<>();
        for (int i = 0; i < persons.size(); i++) {
            Person person = persons.get(i);
            objects.add(person);
        }
        for (int i = 0; i < objects.size(); i++) {
            Person person = (Person) objects.get(i);
            System.out.println(person.name);
        }
    }

    public static void main(String[] args) {
        createExtendsListViaList();
        createExtendsListViaArray();
    }
}
