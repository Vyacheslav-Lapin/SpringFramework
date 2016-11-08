import lab.aop.AopLog;
import lab.model.Bar;
import lab.model.Customer;
import lab.model.CustomerBrokenException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertTrue;


//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:application-context.xml")
public class AopAspectJExceptionTest {

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

//        customer.setBroke(true);
    }

    @Test(expected=CustomerBrokenException.class)
    public void testAfterThrowingAdvice() {
 
    	bar.sellSquishee(customer);
    	
        assertTrue("Customer is not broken ", AopLog.getStringValue().contains("Hmmm..."));
        System.out.println(AopLog.getStringValue());
    }
}