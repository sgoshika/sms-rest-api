package com.example.studentmanagement.student;

import lombok.Data; // For automatically generating getters, setters, equals, hashCode, and toString

/**
 * A Data Transfer Object (DTO) used for updating student information.
 * This class encapsulates the fields that are allowed to be updated by a client
 * for a specific student.
 *
 */
@Data // Generates getters, setters, equals, hashCode, and toString methods
public class StudentUpdateDto {

    private String name;  // Represents the student's updated name
    private String email; // Represents the student's updated email

    // You can add more fields here if your update operation allows for modifying
    // other student attributes (e.g., address, phone number).

    // Note: Lombok's @Data annotation automatically generates the constructor,
    // getters, and setters for the fields, so you don't need to write them manually.

}
