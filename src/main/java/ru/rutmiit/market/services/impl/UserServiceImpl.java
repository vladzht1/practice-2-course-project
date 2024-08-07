package ru.rutmiit.market.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.rutmiit.market.domain.User;
import ru.rutmiit.market.dtos.UserDto;
import ru.rutmiit.market.dtos.api.AddUserDto;
import ru.rutmiit.market.dtos.api.UpdateUserDto;
import ru.rutmiit.market.exceptions.OperationFailedException;
import ru.rutmiit.market.exceptions.UserNotFoundException;
import ru.rutmiit.market.repositories.UserRepository;
import ru.rutmiit.market.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    private ModelMapper mapper;

    public UserServiceImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map((user) -> mapper.map(user, UserDto.class)).toList();
    }

    @Override
    public Optional<UserDto> getById(int id) {
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(mapper.map(userOpt.get(), UserDto.class));
    }

    @Override
    public UserDto add(AddUserDto addUserDto) {
        User user = mapper.map(addUserDto, User.class);
        user.setIsActive(true);
        return mapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public UserDto update(UpdateUserDto updateUserDto) {
        Optional<User> userOpt = userRepository.findById(updateUserDto.getId());

        if (userOpt.isEmpty()) {
            throw new UserNotFoundException(updateUserDto.getId());
        }

        User user = userOpt.get();
        user.setFirstName(updateUserDto.getFirstName());
        user.setMiddleName(updateUserDto.getMiddleName());
        user.setLastName(updateUserDto.getLastName());
        user.setEmail(updateUserDto.getEmail());

        if (userRepository.update(user).isEmpty()) {
            throw new OperationFailedException("Error updating user");
        }

        return mapper.map(user, UserDto.class);
    }

    @Override
    public void remove(int id) throws UserNotFoundException {
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isEmpty()) {
            throw new UserNotFoundException(id);
        }

        User user = userOpt.get();

        if (!user.getIsActive()) {
            throw new UserNotFoundException("User was deleted");
        }

        user.setIsActive(false);
        userRepository.update(user);
    }
}
