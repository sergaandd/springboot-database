package shpp.mentor.springbootdatabase.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> getById(@PathVariable Long id
            , @Parameter(hidden = true) Locale locale) {
        return taskService.findById(id, locale);
    }

    @PostMapping("/todo")
    @ApiOperation("Adding new task in H2")
    public ResponseEntity<Object> add(@Valid @RequestBody TaskEntity task,Locale locale) {
        return taskService.add(task,locale);
    }

    @PutMapping("/todo/")
    @ApiOperation("Change task data")
    public ResponseEntity<Object> updateTask(@Valid @RequestBody TaskEntity task
            , @Parameter(hidden = true) Locale locale) {
        return taskService.updateById(task, locale);
    }

    @PutMapping("/todo/{id}")
    @ApiOperation("Change task data")
    public ResponseEntity<Object> updateNextStatus(@PathVariable Long id) {
        return taskService.setNextStatus(id);
    }

    @DeleteMapping("/todo/{id}")
    @ApiOperation("Delete task by id from H2")
    public ResponseEntity<Object> deleteById(@PathVariable Long id
            , @Parameter(hidden = true) Locale locale) {
        return taskService.deleteById(id, locale);
    }
}
