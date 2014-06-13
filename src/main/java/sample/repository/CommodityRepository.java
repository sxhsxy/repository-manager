package sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.entity.Commodity;

/**
 * Created by Xiaohu on 14-6-12.
 */
public interface CommodityRepository extends JpaRepository<Commodity, Long> {
    public Commodity findByName(String name);
}
