package com.geekbrains.knigopoisk.repositories.specifications;

import com.geekbrains.knigopoisk.entities.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {
    public static Specification<User> usernameLike(String usernamePart) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("username"), String.format("%%%s%%", usernamePart));
    }
}