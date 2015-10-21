package io.github.xylsh.junit;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by apple on 15-4-8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/dao.xml",
        "classpath:spring/service.xml",
        "classpath:spring/config.xml"
})
public abstract class AbstractSpringJUnite4Test {
}
