package com.nhnacademy.springboot.familyProject.exception;

public class FamilyRelationshipNotFoundException extends RuntimeException {
    public FamilyRelationshipNotFoundException() {
        super("familyRelationship is not found!");
    }
}
