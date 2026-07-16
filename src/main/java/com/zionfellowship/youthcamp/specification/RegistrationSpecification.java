package com.zionfellowship.youthcamp.specification;

import com.zionfellowship.youthcamp.entity.Registration;
import com.zionfellowship.youthcamp.enums.CampGroup;
import org.springframework.data.jpa.domain.Specification;

public final class RegistrationSpecification {

    private RegistrationSpecification() {
    }

    public static Specification<Registration> search(
            String search
    ) {

        return (root, query, criteriaBuilder) -> {

            if (search == null || search.isBlank()) {
                return criteriaBuilder.conjunction();
            }

            String value =
                    "%" + search.toLowerCase() + "%";

            return criteriaBuilder.or(
                    criteriaBuilder.like(
                            criteriaBuilder.lower(
                                    root.get("name")
                            ),
                            value
                    ),
                    criteriaBuilder.like(
                            criteriaBuilder.lower(
                                    root.get("email")
                            ),
                            value
                    ),
                    criteriaBuilder.like(
                            criteriaBuilder.lower(
                                    root.get("phoneNumber")
                            ),
                            value
                    )
            );
        };
    }

    public static Specification<Registration> group(
            CampGroup group
    ) {

        return (root, query, criteriaBuilder) -> {

            if (group == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(
                    root.get("group"),
                    group
            );
        };
    }

    public static Specification<Registration> checkedIn(
            Boolean checkedIn
    ) {

        return (root, query, criteriaBuilder) -> {

            if (checkedIn == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(
                    root.get("checkedIn"),
                    checkedIn
            );
        };
    }
}