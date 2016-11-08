import lab.model.Country;
import lab.model.Person;
import lab.model.UsualPerson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class SpringTCFAppTest {
	
	@Autowired
	private Person person1;
	private Person expectedPerson;

	@Before
	public void setUp() throws Exception {
		expectedPerson = getExpectedPerson();
	}

	@Test
	public void testInitPerson() {
		assertEquals(expectedPerson, person1);
		System.out.println(person1);
	}

	private UsualPerson getExpectedPerson() {
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
