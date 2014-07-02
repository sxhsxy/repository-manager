package sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.entity.Supplier;

import java.util.List;

/**
 * Created by xiaohu on 14-6-2.
 */
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    public Supplier findByName(String name);
    public List<Supplier> findByNameContaining(String keyword);
}
