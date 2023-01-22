package ru.gentle.distributive.cupboard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gentle.distributive.cupboard.entities.MyUser;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByNicknameIgnoreCaseAndMailIgnoreCase(String nickname, String mail);

    Optional<MyUser> findByNicknameIgnoreCase(String nickname);

    Optional<MyUser> findByUsername(String name);
}
