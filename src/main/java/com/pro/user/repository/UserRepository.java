package com.pro.user.repository;

import com.pro.user.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {
    // You can define custom query methods here if needed
}
