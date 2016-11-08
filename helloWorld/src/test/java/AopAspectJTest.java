import lab.aop.AopLog;
import lab.model.ApuBar;
import lab.model.Bar;
import lab.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// TODO: 08/11/2016 почему не находит в мейне конфиг?
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:application-context1.xml")
public class AopAspectJTest {

//	@Autowired
    private Bar bar;
    
//	@Autowired
    private Customer customer;

    @Before
    public void setUp() throws Exception {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext(
                        "application-context1.xml");

        bar = applicationContext.getBean(Bar.class);
        customer = applicationContext.getBean(Customer.class);

        bar.sellSquishee(customer);
    }

    @Test
    public void testBeforeAdvice() {
        assertTrue("Before advice is not good enough...",
                AopLog.getStringValue().contains("Hello"));
        assertTrue("Before advice is not good enough...", AopLog.getStringValue().contains("How are you doing?"));
        System.out.println(AopLog.getStringValue());
    }

    @Test
    public void testAfterAdvice() {
        System.out.println(AopLog.getStringValue());
        assertTrue("After advice is not good enought...", AopLog.getStringValue().contains("Good Bye!"));
    }

    @Test
    public void testAfterReturningAdvice() {
        assertTrue("Customer is broken", AopLog.getStringValue().contains("Good Enough?"));
        System.out.println(AopLog.getStringValue());
    }

    @Test
    public void testAroundAdvice() {
        assertTrue("Around advice is not good enought...", AopLog.getStringValue().contains("Hi!"));
        assertTrue("Around advice is not good enought...", AopLog.getStringValue().contains("See you!"));
        System.out.println(AopLog.getStringValue());
    }

    @Test
    public void testAllAdvices() {
        assertFalse("barObject instanceof ApuBar", bar instanceof ApuBar);
    }
}