package com.example.streamversebe.Repository.Interface;

import com.example.streamversebe.Model.Entity.Servers;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServerRepository extends MongoRepository<Servers, ObjectId> {
    List<Servers> findByName(String name);

    boolean existsServersByName(String name);

}
