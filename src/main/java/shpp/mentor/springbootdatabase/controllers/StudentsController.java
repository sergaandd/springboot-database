package shpp.mentor.springbootdatabase.controllers;

import io.swagger.annotations.ApiOperation;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shpp.mentor.springbootdatabase.entity.StudentsEntity;
import shpp.mentor.springbootdatabase.repository.StudentRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/students")
public class StudentsController {
    private StudentRepository studentsRepository;

    public StudentsController(StudentRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

        @GetMapping("/getAll")
        @ApiOperation("Getting a list of objects Student")
        public List<StudentsEntity> getAll() {
            List<StudentsEntity> students = studentsRepository.findAll();
            return students;
        }

        @GetMapping("/{id}")
        @ApiOperation("Getting object Student by id")
        public ResponseEntity<StudentsEntity> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(studentsRepository.findById(id).get());
        } catch (NoSuchElementException e){
            return new ResponseEntity("Student with id = "+id+" does not found.", HttpStatus.NOT_ACCEPTABLE);
        }
        }

        @PostMapping()
        @ApiOperation("Adding new object Student in H2")
        public ResponseEntity add(@Valid @RequestBody StudentsEntity student, BindingResult bindingResult) {
            if (bindingResult.hasErrors()){
                return new ResponseEntity("Object has errors.", HttpStatus.NOT_ACCEPTABLE);
        }
            return ResponseEntity.ok(studentsRepository.save(student));
        }

        @PutMapping("/update")
        @ApiOperation("Updating object Student in H2")
        public ResponseEntity update(@Valid @RequestBody StudentsEntity student, BindingResult bindingResult){
            try {
                if (studentsRepository.findById(student.getId()).isEmpty()) {
                    return new ResponseEntity("Object not exists.", HttpStatus.NOT_ACCEPTABLE);
                }
                }catch(NoSuchElementException e){
                    return new ResponseEntity("Object not exists Except.", HttpStatus.NOT_ACCEPTABLE);
            }
            if (student.getId() == null || student.getId() == 0) {
                return new ResponseEntity("illegal param: id", HttpStatus.NOT_ACCEPTABLE);
            }
            if (student.getFirstName() == null || student.getFirstName().trim().length() == 0) {
                return new ResponseEntity("illegal param: First name", HttpStatus.NOT_ACCEPTABLE);
            }
            if (student.getLastName() == null || student.getLastName().trim().length() == 0) {
                return new ResponseEntity("illegal param: Last name", HttpStatus.NOT_ACCEPTABLE);
            }
            if (student.getIpn() == null || bindingResult.hasErrors()) {
                return new ResponseEntity("illegal param: Ipn", HttpStatus.NOT_ACCEPTABLE);
            }
                return ResponseEntity.ok(studentsRepository.save(student));

    }

        @DeleteMapping("/{id}")
        @ApiOperation("Delete object Student by id from H2")
        public ResponseEntity deleteById(@PathVariable Long id){
            try {
                studentsRepository.deleteById(id);
            }
            catch(EmptyResultDataAccessException e){
                e.printStackTrace();
                return new ResponseEntity("Student with id = "+id+" does not found.", HttpStatus.NOT_ACCEPTABLE);
            }
                return new ResponseEntity("Student with id = "+id+" successfully deleted.",HttpStatus.OK);
    }
}
