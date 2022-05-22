package io.sstol.cosmosdb.slices.users.api.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sergey Stol
 * 2022-05-21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String id;
    private String firstName;
    private String lastName;
    private String address;
}