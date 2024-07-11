package ru.rutmiit.market.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.rutmiit.market.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
