package tk;


import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import tk.techforge.springdemo.SBDApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Configuration.class})
@WebAppConfiguration
@ContextConfiguration(classes = {SBDApplication.class})
public class Base {

    @Before
    public void setUp() {
    }


    @Autowired
    protected ApplicationContext ctx;


}
