import lab.model.Person;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.GenericGroovyApplicationContext;

import static org.junit.Assert.assertEquals;

public class SimpleAppTest {
	
//	private static final String APPLICATION_CONTEXT_XML_FILE_NAME = "application-context1.xml";
//	private BeanFactory context = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML_FILE_NAME);
	private BeanFactory context =
		new GenericGroovyApplicationContext("classpath:beans.groovy");
	private Person expectedPerson = TestUtils.getExpectedPerson();

	@Test
	public void testInitPerson() {
		assertEquals(expectedPerson, context.getBean("person", Person.class));
	}
}
