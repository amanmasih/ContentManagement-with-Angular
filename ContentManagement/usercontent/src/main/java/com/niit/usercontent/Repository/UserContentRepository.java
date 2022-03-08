package com.niit.usercontent.Repository;

import com.niit.usercontent.Domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserContentRepository extends MongoRepository<User,String> {
}
