package sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.entity.PurchaseRecord;
import sample.entity.PurchaseRecordDetail;
import sample.repository.CommodityRepository;
import sample.repository.PurchaseRecordDetailRepository;
import sample.repository.PurchaseRecordRepository;
import sample.repository.SupplierRepository;

import java.util.Collection;
import java.util.List;

/**
 * Created by xiaohu on 14-6-14.
 */
@Service
@Transactional
public class PurchaseService {
    @Autowired
    private PurchaseRecordRepository purchaseRecordRepository;
    @Autowired
    private PurchaseRecordDetailRepository purchaseRecordDetailRepository;
    @Autowired
    private CommodityRepository commodityRepository;
    @Autowired
    private SupplierRepository supplierRepository;

    public PurchaseRecord findPurchaseRecordEager(PurchaseRecord purchase) {
        purchase = purchaseRecordRepository.findOne(purchase.getId());
        purchase.getPurchaseRecordDetails().size();
        return purchase;
    }

    public void delete(PurchaseRecord purchaseRecord) {
        purchaseRecord = findPurchaseRecordEager(purchaseRecord);
        Collection<PurchaseRecordDetail> details = purchaseRecord.getPurchaseRecordDetails();
        purchaseRecordDetailRepository.delete(details);
        purchaseRecordRepository.delete(purchaseRecord);
    }
}
