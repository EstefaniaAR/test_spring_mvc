package com.example.test_thymeleaf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import com.example.test_thymeleaf.domain.Rol;
import com.example.test_thymeleaf.domain.UserEntity;
import com.example.test_thymeleaf.repository.UserRepository;

@Service("jpaUserDetailsService")
@Transactional(readOnly=true)
public class UserService implements UserDetailsService{

	@Autowired
	UserRepository ur;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		UserEntity user = ur.findByUsername(username);
		List <GrantedAuthority>authorities = new ArrayList<GrantedAuthority>();
		
		for (Rol r: user.getRoles())
		{
			authorities.add(new SimpleGrantedAuthority(r.getAuthority()));
		}
		return new User(user.getUsername(),user.getPassword(),user.isEnabled(),true,true,true,authorities);
	}

}
