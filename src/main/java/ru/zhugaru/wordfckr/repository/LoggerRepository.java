package ru.zhugaru.wordfckr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.zhugaru.wordfckr.entity.LoggerEntity;

@Repository
public interface LoggerRepository extends MongoRepository<LoggerEntity, String> {
}
