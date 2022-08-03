import com.zistrong.javaee8.service.MailService;
import org.junit.Test;

public class MailTest {


    @Test
    public void testMail() {
        new MailService().testMail();
    }

}
