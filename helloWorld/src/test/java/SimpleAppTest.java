import lab.model.Country;
import lab.model.Person;
import lab.model.UsualPerson;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class SimpleAppTest {

    private static final String APPLICATION_CONTEXT_XML_FILE_NAME =
            "application-context.xml";

    private AbstractApplicationContext context;

    private Person expectedPerson;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext(
                APPLICATION_CONTEXT_XML_FILE_NAME);
        expectedPerson = getExpectedPerson();
    }

    @Test
    public void testInitPerson() {
        Person person = context.getBean(Person.class);
        System.out.println(person);
        assertEquals(expectedPerson, person);
    }

    private Person getExpectedPerson() {
        return new UsualPerson()
                .setAge(35)
                .setHeight(1.78F)
                .setProgrammer(true)
                .setName("John Smith")
                .setCountry(new Country()
                        .setId(1)
                        .setName("Russia")
                        .setCodeName("RU"))
                .setContacts(
                        Arrays.asList(
                                "asd@asd.ru",
                                "+7-234-456-67-89")
                );
    }
}
