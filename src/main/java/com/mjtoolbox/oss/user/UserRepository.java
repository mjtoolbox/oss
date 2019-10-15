package com.mjtoolbox.oss.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserOss, Long> {
    UserOss findByEmail(String email);
}
