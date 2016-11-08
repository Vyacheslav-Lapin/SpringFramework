package lab.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import lab.model.Customer;
import lab.model.Squishee;

@Aspect
public class Politeness {

    private static final String POINTCUT_SQUISHEE = "execution(* sellSquishee(..))";

    @Before(POINTCUT_SQUISHEE)
    public void sayHello(JoinPoint joinPiont) {
        AopLog.append("Hello " + ((Customer) joinPiont.getArgs()[0]).getName() + ". How are you doing? \n");
    }

    @AfterReturning(pointcut = POINTCUT_SQUISHEE,
            returning = "retVal", argNames = "retVal")
    public void askOpinion(Object retVal) {
        AopLog.append("Is " + ((Squishee) retVal).getName() + " Good Enough? \n");
    }

    @AfterThrowing(POINTCUT_SQUISHEE)
    public void sayYouAreNotAllowed() {
        AopLog.append("Hmmm... \n");
    }

    @After(POINTCUT_SQUISHEE)
    public void sayGoodBye() {
        AopLog.append("Good Bye! \n");
    }

    @Around(POINTCUT_SQUISHEE)
    public Object sayPoliteWordsAndSell(ProceedingJoinPoint pjp) throws Throwable {
        AopLog.append("Hi! \n");
        Object retVal = pjp.proceed();
        AopLog.append("See you! \n");
        return retVal;
    }

}