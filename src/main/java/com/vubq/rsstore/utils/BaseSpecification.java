package com.vubq.rsstore.utils;

import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

@AllArgsConstructor
public class BaseSpecification<T> implements Specification<T> {

    private static final long serialVersionUID = 1L;

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if ((!criteria.getOperation().equals(SearchOperation.IN) && criteria.getValue() == null)) {
            return null;
        }
        Path<T> path = root;
        for (String key : criteria.getKeys()) {
            path = path.get(key);
        }
        switch (criteria.getOperation()) {
            case EQUALITY:
                if (StringUtils.isEmpty(criteria.getValue())) {
                    return null;
                }
                return builder.equal(path, criteria.getValue());
            case NEGATION:
                return builder.notEqual(path, criteria.getValue());
            case GREATER_THAN:
                return builder.greaterThan(path.as(String.class), criteria.getValue().toString());
            case GREATER_THAN_OR_EQUAL:
                return builder.greaterThanOrEqualTo(path.as(String.class), criteria.getValue().toString());
            case LESS_THAN:
                return builder.lessThan(path.as(String.class), criteria.getValue().toString());
            case LESS_THAN_OR_EQUAL:
                return builder.lessThanOrEqualTo(path.as(String.class), criteria.getValue().toString());
            case LIKE:
                if (StringUtils.isEmpty(criteria.getValue())) {
                    return null;
                }
                return builder.like(path.as(String.class), criteria.getValue().toString());
            case STARTS_WITH:
                if (StringUtils.isEmpty(criteria.getValue())) {
                    return null;
                }
                return builder.like(path.as(String.class), criteria.getValue() + "%");
            case ENDS_WITH:
                if (StringUtils.isEmpty(criteria.getValue())) {
                    return null;
                }
                return builder.like(path.as(String.class), "%" + criteria.getValue());
            case CONTAINS:
                if (StringUtils.isEmpty(criteria.getValue())) {
                    return null;
                }
                return builder.like(builder.upper(path.as(String.class)), "%" + criteria.getValue() + "%");
            case IN:
                if (criteria.getListValue() == null || criteria.getListValue().size() == 0) {
                    return null;
                }
                return path.in(criteria.getListValue());

            default:
                return null;
        }
    }
}
