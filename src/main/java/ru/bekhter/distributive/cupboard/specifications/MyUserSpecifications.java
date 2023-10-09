package ru.bekhter.distributive.cupboard.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.bekhter.distributive.cupboard.entities.MyUser;

public class MyUserSpecifications {

    public static Specification<MyUser> nicknameLike(String nickname) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("nickname")), String.format("%%%s%%", nickname.toLowerCase()));
    }
}