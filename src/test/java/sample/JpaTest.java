package sample;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import sample.entity.Commodity;
import sample.repository.CommodityRepository;

import java.util.List;

/**
 * Created by xiaohu on 14-6-12.
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class JpaTest {
    @Autowired
    private CommodityRepository commodityRepository;

    @Before
    public void prepareData() {
        ;
    }

    @Test
    public void testJpaDao() {
        List<Commodity> list = commodityRepository.findAll();
        for(Commodity c : list) {
            System.out.println(c);
        }
    }
}