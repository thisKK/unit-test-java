package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@Table(
//   uniqueConstraints = @UniqueConstraint(columnNames = {"PERSON_ID"})
)
public class Person {

    @GeneratedValue
    @Id
    private Long id;

    @NotNull
    @Pattern(regexp = "\\d{13}")
    private String PersonId;

    @NotNull
    @Size(max = 10)
    private String fristName;

    @NotNull
    @Size(max = 10)
    private String lastName;

    @NotNull
    private  String email;

    @NotNull
    private Double hight;

    @NotNull
    private Double wight;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getPersonId() {
        return PersonId;
    }

    public void setPersonId(String personId) {
        PersonId = personId;
    }

    public String getFristName() {
        return fristName;
    }

    public void setFristName(String fristName) {
        this.fristName = fristName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getHight() {
        return hight;
    }

    public void setHight(Double hight) {
        this.hight = hight;
    }

    public Double getWight() {
        return wight;
    }

    public void setWight(Double wight) {
        this.wight = wight;
    }

}
