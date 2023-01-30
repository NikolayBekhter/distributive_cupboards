package ru.gentle.distributive.cupboard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gentle.distributive.cupboard.entities.DistributiveBox;

import java.util.Optional;

@Repository
public interface DistributiveBoxRepository extends JpaRepository<DistributiveBox, Long> {
    Optional<DistributiveBox> findByBoxNumber(String boxNumber);

    void deleteByBoxNumber(String boxNumber);
}
