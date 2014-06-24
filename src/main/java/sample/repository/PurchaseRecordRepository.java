package sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.entity.PurchaseRecord;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Xiaohu on 14-6-13.
 */
public interface PurchaseRecordRepository extends JpaRepository<PurchaseRecord, Long> {
    public List<PurchaseRecord> findByDatetimeBetween(Timestamp start, Timestamp end);
}
