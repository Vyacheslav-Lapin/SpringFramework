package lab.model;

public interface Person<T extends Person<T>> {
    T setName(String name);
    String getName();
    void sayHello(Person person);
}