package sample;

import sample.entity.Permission;
import sample.entity.User;
import sample.repository.UserRepository;
import sample.service.SystemService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Xiaohu on 14-4-24.
 */
@Service
public class SystemAuthorizingRealm extends AuthorizingRealm {
    @Autowired
    UserRepository userRepository;
    @Autowired
    SystemService systemService;

    private static final Logger logger = LoggerFactory.getLogger(SystemAuthorizingRealm.class);

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        User user = systemService.findUserByLoginName((String) token.getPrincipal());
        if(user != null) {
           return new SimpleAuthenticationInfo(user.getLoginName(), user.getPassword(), getName());
        }
        else return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String loginName = (String) principals.getPrimaryPrincipal();
        List<Permission> permissions = systemService.findPermissionByLoginName(loginName);
        if (permissions != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            for (Permission p : permissions) {
                info.addStringPermission(p.getName());
            }
            return info;
        }
        else return null;
    }


}
