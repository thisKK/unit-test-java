package com.example.demo;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.demo.entity.*;
import com.example.demo.repository.*;


@DataJpaTest
public class PersonTests {

    private Validator validator;

//    ตัวอย่างการ Autowired
    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void testPersonSaveSucess() {
        Person person = new Person();
        String personId = "1234567890123";
        String fristName = "piampoon";
        String lastName = "poonpiam";
        String email = "sonicrat12@gmail.com";
        Double hight = 170.6;
        Double wight = 50.6;
        person.setPersonId(personId);
        person.setFristName(fristName);
        person.setLastName(lastName);
        person.setEmail(email);
        person.setHight(hight);
        person.setWight(wight);

        person = personRepository.saveAndFlush(person);
        Optional<Person> found = personRepository.findById(person.getId());

        assertEquals(personId,found.get().getPersonId());
        assertEquals(fristName,found.get().getFristName());
        assertEquals(lastName,found.get().getLastName());
        assertEquals(email,found.get().getEmail());
        assertEquals(hight,found.get().getHight());
        assertEquals(wight,found.get().getWight());
    }

    @Test
    void testPersonIdMustNotBeNull() {
        Person person = new Person();
        String personId = null;
        String fristName = "piampoon";
        String lastName = "poonpiam";
        String email = "sonicrat12@gmail.com";
        Double hight = 170.6;
        Double wight = 50.6;
        person.setPersonId(personId);
        person.setFristName(fristName);
        person.setLastName(lastName);
        person.setEmail(email);
        person.setHight(hight);
        person.setWight(wight);

        Set<ConstraintViolation<Person>> result = validator.validate(person);
        //result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Person> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("PersonId", v.getPropertyPath().toString());
    }
    @Test
    void testPersonIdMustNotBePatten(){
        Person person = new Person();
        String personId = "12345678901234";
        String fristName = "piampoon";
        String lastName = "poonpiam";
        String email = "sonicrat12@gmail.com";
        Double hight = 170.6;
        Double wight = 50.6;
        person.setPersonId(personId);
        person.setFristName(fristName);
        person.setLastName(lastName);
        person.setEmail(email);
        person.setHight(hight);
        person.setWight(wight);

        Set<ConstraintViolation<Person>> result = validator.validate(person);
        assertEquals(1,result.size());

        ConstraintViolation<Person> v = result.iterator().next();
        assertEquals("must match \"\\d{13}\"",v.getMessage());
        assertEquals("PersonId",v.getPropertyPath().toString());
    }
    @Test
    void testPersonFirstNameSizeMax10(){
        Person person = new Person();
        String personId = "1234567890123";
        String fristName = "piampoonpiampoon";
        String lastName = "poonpiam";
        String email = "sonicrat12@gmail.com";
        Double hight = 170.6;
        Double wight = 50.6;
        person.setPersonId(personId);
        person.setFristName(fristName);
        person.setLastName(lastName);
        person.setEmail(email);
        person.setHight(hight);
        person.setWight(wight);

        Set<ConstraintViolation<Person>> result = validator.validate(person);
        assertEquals(1,result.size());

        ConstraintViolation<Person> v = result.iterator().next();
        assertEquals("size must be between 0 and 10",v.getMessage());
        assertEquals("fristName",v.getPropertyPath().toString());
    }
    @Test
    void testPersonLastNameSizeMax10() {
        Person person = new Person();
        String personId = "1234567890123";
        String fristName = "piampoon";
        String lastName = "poonpiampoonpiam";
        String email = "sonicrat12@gmail.com";
        Double hight = 170.6;
        Double wight = 50.6;
        person.setPersonId(personId);
        person.setFristName(fristName);
        person.setLastName(lastName);
        person.setEmail(email);
        person.setHight(hight);
        person.setWight(wight);

        Set<ConstraintViolation<Person>> result = validator.validate(person);
        assertEquals(1,result.size());

        ConstraintViolation<Person> v = result.iterator().next();
        assertEquals("size must be between 0 and 10",v.getMessage());
        assertEquals("lastName",v.getPropertyPath().toString());
    }
}


















