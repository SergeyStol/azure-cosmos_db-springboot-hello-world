package io.sstol.cosmosdb.slices.users.service;

import com.azure.cosmos.implementation.InternalServerErrorException;
import com.azure.cosmos.implementation.NotFoundException;
import io.sstol.cosmosdb.slices.users.api.v1.dto.UserDto;
import io.sstol.cosmosdb.slices.users.db.UsersRepository;
import io.sstol.cosmosdb.slices.users.db.daos.UserDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static io.sstol.cosmosdb.common.exceptions.ExceptionMessages.*;

/**
 * @author Sergey Stol
 * 2022-05-21
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UsersService {
    private final UsersRepository repo;
    private final ModelMapper modelMapper;

    public UserDto getUser(String id) {
        return repo.findById(id)
                .map(this::convertToUserDto)
                .blockOptional()
                .orElseThrow(() -> new NotFoundException(CANT_FIND_USER_WITH_ID));
    }

    public List<UserDto> getUsers() {
        List<UserDao> usersDao = repo.findAll().collectList().block();
        if (usersDao == null)
            throw new InternalServerErrorException("Can't get all users.");
        return usersDao.stream()
                .map(this::convertToUserDto)
                .collect(Collectors.toList());
    }

    public String addUser(UserDto userDto) {
        UserDao userDao = repo.save(convertToUserDao(userDto)).doOnError(onError -> log.error(onError.getMessage())).block();
        if (userDao == null) {
            throw new InternalServerErrorException("Can't save user. UserDao is null.");
        }
        String userDaoId = userDao.getId();
        if (userDaoId == null || userDaoId.isBlank()) {
            throw new InternalServerErrorException("User id is empty or blank.");
        }
        log.debug("Returned user id - {}", userDao.getId());
        return userDaoId;
    }

    public void updateUser(UserDto userDto) {
        repo.save(convertToUserDao(userDto)).block();
    }

    public void deleteUser(String id) {
        repo.deleteById(id).block();
    }

    public UserDto convertToUserDto(UserDao userDao) {
        return modelMapper.map(userDao, UserDto.class);
    }

    public UserDao convertToUserDao(UserDto userDto) {
        return modelMapper.map(userDto, UserDao.class);
    }

    public List<UserDto> getUserByFirstName(String firstName) {
        List<UserDao> usersDao = repo.findByFirstName(firstName).collectList().block();
        if (usersDao == null)
            throw new InternalServerErrorException("Can't get all users.");
        return usersDao.stream()
                .map(this::convertToUserDto)
                .collect(Collectors.toList());
    }
}