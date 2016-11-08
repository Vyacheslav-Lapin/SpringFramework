import lab.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import configuration.HelloWorldConfig;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:application-context.xml")
@ContextConfiguration(classes = HelloWorldConfig.class)
public class SpringTCFAppTest {
	
	@Resource
	private Person person;

	private Person expectedPerson = TestUtils.getExpectedPerson();

	@Test
	public void testInitPerson() {
		assertEquals(expectedPerson, person);
	}
}
