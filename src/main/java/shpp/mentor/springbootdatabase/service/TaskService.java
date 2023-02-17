package shpp.mentor.springbootdatabase.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import shpp.mentor.springbootdatabase.controllers.Status;
import shpp.mentor.springbootdatabase.entity.TaskEntity;
import shpp.mentor.springbootdatabase.models.ErrorDTO;
import shpp.mentor.springbootdatabase.models.SuccessDTO;
import shpp.mentor.springbootdatabase.repository.TaskRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    String operationTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public List<TaskEntity> getAll() {
        return taskRepository.findAll();
    }

    public ResponseEntity<Object> add(TaskEntity taskEntity,Locale locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("lang", locale) ;
        Optional<TaskEntity> findResult = taskRepository.findById(taskEntity.getId());
        if (findResult.isEmpty()) {
            return ResponseEntity.ok(taskRepository.save(taskEntity));
        }
        ErrorDTO message = new ErrorDTO(operationTime, HttpStatus.BAD_REQUEST.value()
                ,resourceBundle.getString("task_creation_error")
                , resourceBundle.getString("task_creation_error_message"));
        return ResponseEntity
                .badRequest()
                .body(message);
    }

    public ResponseEntity<Object> findById(Long id,Locale locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("lang", locale) ;
        if (taskRepository.findById(id).isPresent()) {
            return new ResponseEntity<>(taskRepository.findById(id), HttpStatus.OK);
        } else {
            ErrorDTO message = new ErrorDTO(operationTime, HttpStatus.BAD_REQUEST.value()
                    ,resourceBundle.getString("entity_not_found")
                    ,ResourceBundle.getBundle("lang",locale)
                    .getString("entity_not_found_message")
                    + id);
            return ResponseEntity
                    .badRequest()
                    .body(message);
        }
    }

    public ResponseEntity<Object> updateById(TaskEntity taskEntity, Locale locale) {
        Optional<TaskEntity> findResult = taskRepository.findById(taskEntity.getId());
        ResourceBundle resourceBundle = ResourceBundle.getBundle("lang", locale) ;
        if (findResult.isEmpty()) {
            ErrorDTO message = new ErrorDTO(operationTime, HttpStatus.BAD_REQUEST.value()
                    ,resourceBundle.getString("entity_not_found")
                    , resourceBundle.getString("entity_not_found_message")
                    + taskEntity.getId());
            return ResponseEntity
                    .badRequest()
                    .body(message);
        }
        return ResponseEntity.ok(taskRepository.save(taskEntity));
    }

    public ResponseEntity<Object> setNextStatus(Long id) {
        TaskEntity findResult = taskRepository.getReferenceById(id);
        findResult.setStatus(Status.nextStatus(findResult.getStatus()));
        return ResponseEntity.ok(taskRepository.save(findResult));
    }

    public ResponseEntity<Object> deleteById(Long id, Locale locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("lang", locale) ;
        taskRepository.deleteById(id);
        SuccessDTO message = new SuccessDTO(operationTime, HttpStatus.OK.value()
                , String.format(resourceBundle.getString("success_message"),id));
        return ResponseEntity.ok().body(message);
    }
}
