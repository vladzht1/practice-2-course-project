package ru.rutmiit.market.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.rutmiit.market.dtos.MarketProductDto;
import ru.rutmiit.market.dtos.UserDto;
import ru.rutmiit.market.dtos.api.AddUserDto;
import ru.rutmiit.market.dtos.api.UpdateUserDto;
import ru.rutmiit.market.exceptions.UserNotFoundException;
import ru.rutmiit.market.services.UserRecommendationsService;
import ru.rutmiit.market.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRecommendationsService userRecommendationsService;

    @GetMapping()
    public Iterable<UserDto> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable() int id) throws UserNotFoundException {
        Optional<UserDto> userOpt = userService.getById(id);

        if (userOpt.isEmpty()) {
            throw new UserNotFoundException(id);
        }

        return userOpt.get();
    }

    @GetMapping("/{id}/recommendations")
    public Iterable<MarketProductDto> getUserProductRecommendations(@PathVariable() int id) {
        return userRecommendationsService.findRecommendedProductsForUser(id);
    }

    @PostMapping()
    public UserDto create(@RequestBody AddUserDto addUserDto) {
        return userService.add(addUserDto);
    }

    @PatchMapping()
    public UserDto update(@RequestBody UpdateUserDto updateUserDto) {
        return userService.update(updateUserDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable() int id) throws UserNotFoundException {
        userService.remove(id);
    }
}
