package sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.entity.PurchaseRecordDetail;

/**
 * Created by Xiaohu on 14-6-13.
 */
public interface PurchaseRecordDetailRepository extends JpaRepository<PurchaseRecordDetail, Long> {
}
