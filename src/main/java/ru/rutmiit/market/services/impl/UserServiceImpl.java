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
import ru.rutmiit.market.exceptions.UserNotFoundException;
import ru.rutmiit.market.repositories.UserRepository;
import ru.rutmiit.market.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    // TODO: Remove `new` call
    private ModelMapper mapper = new ModelMapper();

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map((user) -> mapper.map(user, UserDto.class)).toList();
    }

    @Override
    public Optional<UserDto> getById(int id) {
        Optional<User> userOpt = userRepository.findById(id);

        if (!userOpt.isPresent()) {
            return Optional.empty();
        }

        return Optional.of(mapper.map(userOpt.get(), UserDto.class));
    }

    @Override
    public UserDto add(AddUserDto addUserDto) {
        User user = mapper.map(addUserDto, User.class);
        return mapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public UserDto update(UpdateUserDto updateUserDto) {
        Optional<User> userOpt = userRepository.findById(updateUserDto.getId());

        if (!userOpt.isPresent()) {
            throw new UserNotFoundException(updateUserDto.getId());
        }

        User user = userOpt.get();
        user.setFirstName(updateUserDto.getFirstName());
        user.setMiddleName(updateUserDto.getMiddleName());
        user.setLastName(updateUserDto.getLastName());
        user.setEmail(updateUserDto.getEmail());

        if (!userRepository.update(user).isPresent()) {
            // TODO: Implement
            throw new RuntimeException("Error updating user");
        }

        return mapper.map(user, UserDto.class);
    }

    @Override
    public void remove(int id) throws UserNotFoundException {
        Optional<User> userOpt = userRepository.findById(id);

        if (!userOpt.isPresent()) {
            throw new UserNotFoundException(id);
        }

        User user = userOpt.get();

        if (!user.getIsActive()) {
            // TODO: Throw error
            // return false;
        }

        user.setIsActive(false);
        userRepository.update(user);
    }
}
