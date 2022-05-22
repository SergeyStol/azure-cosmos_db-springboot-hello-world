package io.sstol.cosmosdb.slices.users.db;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import io.sstol.cosmosdb.slices.users.db.daos.UserDao;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @author Sergey Stol
 * 2022-05-21
 */
@Repository
public interface UsersRepository extends ReactiveCosmosRepository<UserDao, String> {
    Flux<UserDao> findByFirstName(String firstName);
}
