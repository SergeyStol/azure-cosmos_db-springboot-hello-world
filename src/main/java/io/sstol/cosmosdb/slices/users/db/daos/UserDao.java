package io.sstol.cosmosdb.slices.users.db.daos;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * @author Sergey Stol
 * 2022-05-21
 */
@Container(containerName = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDao {
    @Id
    @GeneratedValue
    private String id;
    private String firstName;
    @PartitionKey
    private String lastName;
    private String address;
}