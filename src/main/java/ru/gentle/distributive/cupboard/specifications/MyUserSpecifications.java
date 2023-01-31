package ru.gentle.distributive.cupboard.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.gentle.distributive.cupboard.entities.MyUser;

public class MyUserSpecifications {

    public static Specification<MyUser> nicknameLike(String nickname) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("nickname"), String.format("%%%s%%", nickname));
    }
}