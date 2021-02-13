package com.geekbrains.knigopoisk.util;

import com.geekbrains.knigopoisk.entities.User;
import com.geekbrains.knigopoisk.repositories.specifications.UserSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@Getter
public class UserFilter {
    private Specification<User> spec;

    public UserFilter(Map<String, String> params) {
        spec = Specification.where(null);

        String filterUsername = params.get("username");
        if (filterUsername != null && !filterUsername.isBlank()) {
            spec = spec.and(UserSpecifications.usernameLike(filterUsername));
        }
    }
}
