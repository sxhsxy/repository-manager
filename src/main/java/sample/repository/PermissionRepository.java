package sample.repository;

import sample.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by xiaohu on 14-5-3.
 */
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    public Permission findByName(String name);
}
