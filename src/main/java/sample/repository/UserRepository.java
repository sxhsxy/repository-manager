package sample.repository;

import sample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Xiaohu on 14-4-3.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByLoginName(String loginname);
}
