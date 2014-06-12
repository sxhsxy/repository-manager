package sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sample.entity.Commodity;

/**
 * Created by xiaohu on 14-6-12.
 */

public interface CommodityRepository extends JpaRepository<Commodity, Long> {
}
