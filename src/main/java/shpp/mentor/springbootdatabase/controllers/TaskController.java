package shpp.mentor.springbootdatabase.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shpp.mentor.springbootdatabase.entity.TaskEntity;
import shpp.mentor.springbootdatabase.service.TaskService;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/todo")
    @ApiOperation("Getting a list of tasks")
    public List<TaskEntity> getAll() {
        return taskService.getAll();
    }


    @GetMapping("/todo/{id}")
    @ApiOperation("Getting an task by id")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @PostMapping("/todo")
    @ApiOperation("Adding new task in H2")
    public ResponseEntity<Object> add(@Valid @RequestBody TaskEntity task, BindingResult bindingResult, Locale c) {
        return taskService.addNew(task, bindingResult);
    }

    @PutMapping("/todo")
    @ApiOperation("Updating task in H2")
    public ResponseEntity<Object> update(@Valid @RequestBody TaskEntity task, BindingResult bindingResult) {
        return taskService.updateById(task, bindingResult);
    }

    @DeleteMapping("/todo/{id}")
    @ApiOperation("Delete task by id from H2")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        return taskService.deleteById(id);
    }
}
