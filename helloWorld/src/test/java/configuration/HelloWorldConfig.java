package configuration;

import lab.model.Person;
import lab.model.UsualPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("person.properties")
@ImportResource(locations = "application-context.xml")
@ComponentScan(basePackages = "lab.model")
public class HelloWorldConfig {

    @Autowired
    Environment env;

    @Bean
    public Person person() {
        return new UsualPerson()
                .setName(env.getProperty("name"))
                .setHeight(Float.parseFloat(env.getProperty("height")))
                .setAge(Integer.parseInt(env.getProperty("age")))
                .setProgrammer(env.getProperty("programmer").equalsIgnoreCase("true"));
    }

//    @Bean
//    public List<String> contacts() {
//        return Arrays.asList("1", "asd@asd.ru", "+7-234-456-67-89");
//    }

//    @Bean
//    public Country country() {
//        return new Country(1, "Russia", "RU");
//    }
}