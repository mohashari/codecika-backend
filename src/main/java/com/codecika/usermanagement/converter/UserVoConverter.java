package com.codecika.usermanagement.converter;

import com.codecika.usermanagement.enums.Gender;
import com.codecika.usermanagement.enums.Role;
import com.codecika.usermanagement.persistence.domain.User;
import com.codecika.usermanagement.util.ExtendedSpringBeanUtil;
import com.codecika.usermanagement.vo.UserVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aditrioka on 3/2/2017.
 */
@Component
public class UserVoConverter extends BaseVoConverter<UserVO, UserVO, User>
        implements IBaseVoConverter<UserVO, UserVO, User> {

    @Override
    public User transferVOToModel(UserVO vo, User model) {
        if (model == null) model = new User();
        super.transferVOToModel(vo, model);

        if (vo.getRole() != null) {
            Role role = Role.valueOf(vo.getRole());
            model.setRole(role);
        }
        if(vo.getGender() !=null){
            Gender gender = Gender.valueOf(vo.getGender());
            model.setGender(gender);
        }

        ExtendedSpringBeanUtil.copySpecificProperties(vo, model,
                new String[]{"username", "firstName", "lastName", "phone", "userEnable",
                        "birthDate","birthDate","deviceId","gender","code"});

        return model;
    }

    @Override
    public UserVO transferModelToVO(User model, UserVO vo) {
        if (vo == null) vo = new UserVO();
        super.transferModelToVO(model, vo);

        if (model.getRole() != null) {
            vo.setRole(model.getRole().getInternalValue());
        }

        if(model.getGender() !=null){
            vo.setGender(model.getGender().getInternalValue());
        }

        ExtendedSpringBeanUtil.copySpecificProperties(model, vo,
                new String[]{"username", "firstName", "lastName", "phone", "userEnable",
                        "birthDate","birthDate","deviceId","gender","code"});

        return vo;
    }

}
