package sample.service;

import sample.entity.*;
import sample.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xiaohu on 14-4-2.
 */
@Service
@Transactional
public class SystemService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    //User

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public Page<User> findUser(PageRequest pageRequest, User user) {
        return userRepository.findAll(pageRequest);
    }

    public User findUserByLoginName(String loginName) {
        return userRepository.findByLoginName(loginName);
    }

    public User findUser(Long id) {
        return userRepository.findOne(id);
    }

    public void deleteUser(User user) {
       userRepository.delete(user);
    }

    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    //Role

    public Role findRoleByNameEager(String name) {
        Role r = roleRepository.findByName(name);
        r.getPermissions().size();
        return r;
    }

    //Permission

    public List<Permission> findPermissionByLoginName(String loginName) {
        User u = userRepository.findByLoginName(loginName);
        if (u != null) {
            ArrayList<Permission> permissions = new ArrayList<Permission>();
            for (Role r : userRepository.findByLoginName(loginName).getRoles()) {
                permissions.addAll(r.getPermissions());
            }
            return permissions;
        }
        else return null;

    }
}
