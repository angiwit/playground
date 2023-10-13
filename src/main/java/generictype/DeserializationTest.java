package generictype;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeserializationTest {

    static class Category {
        private String name;

        Category(String name) {
            this.name = name;
        }

        Category() {

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    static class Hobby<T> {
        private String name;
        private T category;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public T getCategory() {
            return category;
        }

        public void setCategory(T category) {
            this.category = category;
        }
    }

    static class Person {
        private String name;
        private Map<String, Hobby<Category>> hobbies = new HashMap<>();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Map<String, Hobby<Category>> getHobbies() {
            return hobbies;
        }

        public void setHobbies(Map<String, Hobby<Category>> hobbies) {
            this.hobbies = hobbies;
        }
    }

    /**
     * Person1 has generic type, but referenced by list, in this case, parse the code to retrieve the generic type is enough for deserialize.
     */
    static class Person1 {
        private List<Category> names;

        public List<Category> getNames() {
            return names;
        }

        public void setNames(List<Category> names) {
            this.names = names;
        }
    }

    public static void main(String[] args) throws JsonProcessingException {
        deserializeGeneric1();
    }

    /**
     * Person1 directly hold an object with generic type.
     *
     * @throws JsonProcessingException
     */
    public static void deserializeGeneric() throws JsonProcessingException {
        List<Category> names = new ArrayList<>();
        names.add(new Category("cate1"));
        names.add(new Category("cate2"));
        Person1 person1 = new Person1();
        person1.setNames(names);
        ObjectMapper objectMapper = new ObjectMapper();
        String person1String = objectMapper.writeValueAsString(person1);
        Person1 namesD = objectMapper.readValue(person1String, Person1.class);
        System.out.println(namesD.getNames().size());
    }

    /**
     * When using a class directly has generic type reference, a TypeReference need to be used.
     *
     * @throws JsonProcessingException
     */
    public static void deserializeGeneric1() throws JsonProcessingException {
        /**
         * Generic type is determined when running(generic type is erased), so no way to parse a class to retrieve the generic type,
         * In this case, you have to pass the generic type with TypeReference.
         */
        List<Category> names = new ArrayList<>();
        names.add(new Category("cate1"));
        names.add(new Category("cate2"));
        ObjectMapper objectMapper = new ObjectMapper();
        String person1String = objectMapper.writeValueAsString(names);
        List<Category> namesD = objectMapper.readValue(person1String, new TypeReference<List<Category>>() {
        });
        for (int i = 0; i < names.size(); i++) {
            System.out.println(namesD.get(i).getName());
        }
    }

    /**
     * when a generic type used in the object(not directly),
     *
     * @throws JsonProcessingException
     */
    public static void deserializeObject() throws JsonProcessingException {
        Category category = new Category("myCategory");
        Hobby<Category> hobby = new Hobby<>();
        hobby.setName("firstHobby");
        hobby.setCategory(category);
        Map<String, Hobby<Category>> hobbyMap = new HashMap<>();
        hobbyMap.put("firstHobbyInMap", hobby);
        Person person = new Person();
        person.setName("zaniu");
        person.setHobbies(hobbyMap);
        deserialize(new ObjectMapper().writeValueAsString(person));
    }

    public static void deserialize(String person) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Person deserializedPerson = objectMapper.readValue(person, Person.class);
        System.out.println(deserializedPerson.getHobbies().get("firstHobbyInMap").getCategory().getName());
    }
}
