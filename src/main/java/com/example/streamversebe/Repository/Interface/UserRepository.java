package com.example.streamversebe.Repository.Interface;

import com.example.streamversebe.Model.Entity.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<Users, ObjectId> {
    Optional<Users> findByEmail(String email);

    Optional<Users> findByUsername(String username);

    Optional<Users> findByUsernameOrEmail(String username, String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    List<Users> findByLastActiveAfter(LocalDateTime dateTime);

    List<Users> findByFriendsContains(ObjectId userId);
}
