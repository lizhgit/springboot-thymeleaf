package com.lz.service;

import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lz.dao.UserMapper;
import com.lz.entity.User;
@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	public int insertSelective(User record){
		return userMapper.insertSelective(record);
	}
	
	public User selectByNamePwd(User user){
		return userMapper.selectByNamePwd(user);
	}
	public List<User> selectAll(){
		return userMapper.selectAll();
	}
	public List<User> selectByName(String name){
		return userMapper.selectByName(name);
	}
	public User selectByPrimaryKey(int id) {
		return userMapper.selectByPrimaryKey(id);
	}
}
