package sample.repository;

import sample.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by xiaohu on 14-5-3.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByName(String name);
}
