package com.vubq.rsstore.utils;

public enum SearchOperation {

    EQUALITY, NEGATION, GREATER_THAN, GREATER_THAN_OR_EQUAL, LESS_THAN, LESS_THAN_OR_EQUAL, LIKE, STARTS_WITH, ENDS_WITH, CONTAINS, IN;

    public static SearchOperation getSimpleOperation(final char input) {
        switch (input) {
            case ':':
                return EQUALITY;
            case '!':
                return NEGATION;
            case '>':
                return GREATER_THAN;
            case '<':
                return LESS_THAN;
            case '~':
                return LIKE;
            default:
                return null;
        }
    }
}

