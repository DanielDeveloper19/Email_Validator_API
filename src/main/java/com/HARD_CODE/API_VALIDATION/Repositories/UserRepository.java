package com.HARD_CODE.API_VALIDATION.Repositories;


import com.HARD_CODE.API_VALIDATION.Model.UserEntity;
import com.HARD_CODE.API_VALIDATION.Model.ValidationEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserRepository extends JpaRepository<UserEntity,Long> {
}
