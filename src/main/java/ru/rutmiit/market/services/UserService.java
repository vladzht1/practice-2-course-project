package ru.rutmiit.market.services;

import java.util.List;
import java.util.Optional;

import ru.rutmiit.market.dtos.UserDto;
import ru.rutmiit.market.dtos.api.AddUserDto;
import ru.rutmiit.market.dtos.api.UpdateUserDto;
import ru.rutmiit.market.exceptions.UserNotFoundException;

public interface UserService {
    List<UserDto> getAll();
    Optional<UserDto> getById(int id);
    UserDto add(AddUserDto userDto);
    UserDto update(UpdateUserDto userDto);
    void remove(int id) throws UserNotFoundException;
}
