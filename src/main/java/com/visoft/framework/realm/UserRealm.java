package com.visoft.framework.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.omg.CORBA.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;
import com.visoft.framework.entity.BaseUser;

public class UserRealm extends AuthorizingRealm {


    private static final Logger log = LoggerFactory.getLogger("es-error");

  

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        String username = (String) principals.getPrimaryPrincipal();
        BaseUser user =new BaseUser();
        user.setUserName("testOne");

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles=new HashSet<String>();
        roles.add("tester");
        authorizationInfo.setRoles(roles);
        
        Set<String> permissions=new HashSet<String>(); 
        permissions.add("/user/query");
        authorizationInfo.setStringPermissions(permissions);

        return authorizationInfo;
    }

 

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo("test", "one".toCharArray(), getName());
        return info;
    }
}
