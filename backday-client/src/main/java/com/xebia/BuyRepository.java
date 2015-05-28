package com.xebia;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BuyRepository extends MongoRepository<Buy, String> {
}
