import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlatMapTest {

    public static void main(String[] args) {

    }

    public static void testFlatMap() {
        List<List<String>> nestedList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        nestedList.add(list);
        Person person = new Person();
        person.setNestedList(nestedList);
        Optional.ofNullable(person)
                .map(x -> x.getNestedList())
                // Lst result is encapsulated by Optional, so it can't be retrieved directly, use orElse to
                // retrieve the inner nested list.
                .orElse(List.of())
                // stream() here can make outer list to stream form.
                .stream()
                // Stream<List<xxx>> is nested, so it can be flatted by flatMap.
                // Use map here can iterate the inner list.
                .flatMap(x -> x.stream())
                // Use flatMap and the stream changed to string stream, then map can be used here.
                .map(x -> x.toLowerCase())
                .collect(Collectors.toList());
    }

    static class Person {
        private List<List<String>> nestedList;

        public List<List<String>> getNestedList() {
            return nestedList;
        }

        public void setNestedList(List<List<String>> nestedList) {
            this.nestedList = nestedList;
        }
    }
}
