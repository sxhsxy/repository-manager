package sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.entity.PurchaseRecord;

/**
 * Created by Xiaohu on 14-6-13.
 */
public interface PurchaseRecordRepository extends JpaRepository<PurchaseRecord, Long> {
}
