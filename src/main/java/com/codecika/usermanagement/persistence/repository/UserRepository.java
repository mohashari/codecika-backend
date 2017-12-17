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

}
