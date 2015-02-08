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
import sample.entity.PurchaseRecord;
import sample.entity.PurchaseRecordDetail;
import sample.entity.Supplier;
import sample.repository.CommodityRepository;
import sample.repository.*;

import java.lang.System;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by xiaohu on 14-6-12.
 */

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class JpaTest {
    @Autowired
    private CommodityRepository commodityRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private PurchaseRecordRepository purchaseRecordRepository;
    @Autowired
    private PurchaseRecordDetailRepository purchaseRecordDetailRepository;

    @Before
    public void prepareData() {
        Commodity commodity = new Commodity("0000", "香蕉", "Kg", new BigDecimal(5), new BigDecimal(6), 0L, 0L, 0L);
        System.out.println(commodity+ "xxxxxxxxx");
        Commodity commodity2 = new Commodity("0001", "苹果", "Kg", new BigDecimal(5), new BigDecimal(6), 0L, 0L, 0L);
        Commodity commodity3 = new Commodity("0002", "樱桃", "Kg", new BigDecimal(5), new BigDecimal(6), 0L, 0L, 0L);
        commodityRepository.save(commodity);
        commodityRepository.save(commodity2);
        commodityRepository.save(commodity3);
        Supplier supplier = new Supplier("小虎", "13678901234", "浙江杭州");
        supplierRepository.save(supplier);


        PurchaseRecord purchaseRecord = new PurchaseRecord(new Timestamp(System.currentTimeMillis()), supplier);
        purchaseRecord = purchaseRecordRepository.save(purchaseRecord);

        PurchaseRecordDetail purchaseRecordDetail = new PurchaseRecordDetail(purchaseRecord, new BigDecimal(5), 100L, commodityRepository.findByName("香蕉"));
        purchaseRecordDetailRepository.save(purchaseRecordDetail);
    }

    @Test
    public void testJpaDao() {
        List<Commodity> list = commodityRepository.findAll();
        for(Commodity c : list) {
            System.out.println(c.getName());
        }
    }
}