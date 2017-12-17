package com.codecika.usermanagement.service;

import com.codecika.usermanagement.converter.IBaseVoConverter;
import com.codecika.usermanagement.converter.UserVoConverter;
import com.codecika.usermanagement.persistence.domain.User;
import com.codecika.usermanagement.persistence.repository.BaseRepository;
import com.codecika.usermanagement.persistence.repository.UserRepository;
import com.codecika.usermanagement.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;


@Service
public class UserService extends BaseService<User,UserVO,UserVO>{

//    private static final Logger log = Loggi
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserVoConverter userVoConverter;

    @Override
    protected BaseRepository<User> getJpaRepository() {
        return userRepository;
    }

    @Override
    protected JpaSpecificationExecutor<User> getSpecRepository() {
        return userRepository;
    }

    @Override
    protected IBaseVoConverter<UserVO, UserVO, User> getVoConverter() {
        return userVoConverter;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;

}
