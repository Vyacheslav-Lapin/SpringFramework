package lab.model;

public interface Person {
    Person setName(String name);
    String getName();
    void sayHello(Person person);
}