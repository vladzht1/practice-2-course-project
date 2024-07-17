package ru.rutmiit.market.repositories.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ru.rutmiit.market.domain.User;
import ru.rutmiit.market.repositories.UserRepository;

@Repository
public class UserRepositoryDao implements UserRepository {
    @Autowired
    private BaseUserRepository baseRepository;

    @Override
    public List<User> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return baseRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return baseRepository.save(user);
    }

    @Override
    public Optional<User> update(User user) {
        return Optional.of(baseRepository.save(user));
    }
}

@Repository
interface BaseUserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select user from User user order by user.id asc")
    List<User> findAll();
}
