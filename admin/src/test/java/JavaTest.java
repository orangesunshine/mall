import com.orange.MallApplication;
import com.orange.TestApplication;
import com.orange.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
@ContextConfiguration(classes = MallApplication.class)
public class JavaTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void test() {
        System.out.println("null == userMapper " + (null == userMapper));
    }
}
