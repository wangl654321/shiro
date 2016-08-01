package com.atguigu.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm extends AuthorizingRealm{

	/**
	 * 用于授权的 realm 的回调方法. 
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 用于认证的 realm 的回调方法.
	 * 参数 AuthenticationToken 即为登录时调用 Subject 的 login(AuthenticationToken) 方法时传入的参数
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		//1. 把 AuthenticationToken 参数强转为 UsernamePasswordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		
		//2. 从 UsernamePasswordToken 中获取 username
		String username = upToken.getUsername();
		
		//3. 利用 username 查询数据表, 获取用户的信息
		System.out.println("利用页面传入的 " + username + " 从数据库中获取用户信息");
		
		//principal: 认证成功后的实体信息。 可以是 username, 也可以是一个对象. 
		Object principal = username;
		//credentials: 凭证. 即密码. 是用 username 从数据库中获取的!
		Object credentials = "123456";
		//realmName: 当前 Realm 的名字. 直接调用父类的 getName() 方法即可. 
		String realmName = getName();
		
		//4. 利用从数据库中获取的用户信息来创建 SimpleAuthenticationInfo 对象并返回
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, realmName);
		
		return info;
	}

}
