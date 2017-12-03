package com.codecika.usermanagement.persistence.repository;

import com.codecika.usermanagement.enums.Role;
import com.codecika.usermanagement.persistence.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * Created by yukibuwana on 2/2/17.
 */

@Repository
public interface UserRepository extends BaseRepository<User> {

    User findByUsernameIgnoreCase(String username);
    Page<User> findByRole(Role role, Pageable pageable);
    Page<User> findByStatusAdmin(String status, Pageable pageable);
    Page<User> findByRoleAndUsernameLike(Role role, String username, Pageable pageable);
    Page<User> findByStatusAdminAndUsernameLike(String statusAdmin, String username, Pageable pageable);

    Integer countByRole(Role role);

    User findByUsernameIgnoreCaseAndStatusLogin(String username,String status);

}
