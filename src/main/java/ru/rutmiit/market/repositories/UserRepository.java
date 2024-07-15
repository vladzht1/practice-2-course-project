package ru.rutmiit.market.repositories;

import java.util.List;
import java.util.Optional;

import ru.rutmiit.market.domain.User;

public interface UserRepository {
    List<User> findAll();
    Optional<User> findById(Integer id);
    User save(User user);
    Optional<User> update(User user);
}
