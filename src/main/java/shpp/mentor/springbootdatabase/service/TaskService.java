package shpp.mentor.springbootdatabase.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import shpp.mentor.springbootdatabase.controllers.Status;
import shpp.mentor.springbootdatabase.entity.TaskEntity;
import shpp.mentor.springbootdatabase.models.ApiErrorDTO;
import shpp.mentor.springbootdatabase.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskEntity> getAll() {
        return taskRepository.findAll();
    }

    public ResponseEntity<Object> addNew(TaskEntity taskEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return
                    new ResponseEntity<>(new ApiErrorDTO("500",
                            ResourceBundle.getBundle("lang", getLocale()).getString("err"),
                            ResourceBundle.getBundle("lang", getLocale()).getString("err1"))
                            .toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return ResponseEntity.ok(taskRepository.save(taskEntity));
        }
    }

    public ResponseEntity<Object> findById(Long id) {
        if (taskRepository.findById(id).isPresent()) {
            return new ResponseEntity<>(taskRepository.findById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiErrorDTO("500",
                    ResourceBundle.getBundle("lang", getLocale()).getString("err"),
                    ResourceBundle.getBundle("lang", getLocale()).getString("err2"))
                    .toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateById(TaskEntity taskEntity, BindingResult bindingResult) {
        if (taskRepository.findById(taskEntity.getId()).isEmpty() || bindingResult.hasErrors()) {
            return new ResponseEntity<>(new ApiErrorDTO("500",
                    ResourceBundle.getBundle("lang", getLocale()).getString("err"),
                    ResourceBundle.getBundle("lang", getLocale()).getString("err1"))
                    .toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (taskEntity.getName() == null || taskEntity.getName().trim().length() == 0) {
            return new ResponseEntity<>(new ApiErrorDTO("500",
                    ResourceBundle.getBundle("lang", getLocale()).getString("err"),
                    ResourceBundle.getBundle("lang", getLocale()).getString("err3"))
                    .toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (taskEntity.getStart().isAfter(LocalDateTime.now())) {
            return new ResponseEntity<>(new ApiErrorDTO("500",
                    ResourceBundle.getBundle("lang", getLocale()).getString("err"),
                    ResourceBundle.getBundle("lang", getLocale()).getString("err4"))
                    .toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (Status.valueOf(taskRepository.findById(taskEntity.getId()).get().getStatus()).ordinal()
                > 2) {
            return new ResponseEntity<>(new ApiErrorDTO("500",
                    ResourceBundle.getBundle("lang", getLocale()).getString("err"),
                    ResourceBundle.getBundle("lang", getLocale()).getString("err5"))
                    .toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (Status.valueOf(taskRepository.findById(taskEntity.getId()).get().getStatus()).ordinal()
                > Status.valueOf(taskEntity.getStatus()).ordinal()) {
            return new ResponseEntity<>(new ApiErrorDTO("500",
                    ResourceBundle.getBundle("lang", getLocale()).getString("err"),
                    ResourceBundle.getBundle("lang", getLocale()).getString("err6"))
                    .toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(taskRepository.save(taskEntity));
    }

    public ResponseEntity<Object> deleteById(Long id) {
        if (taskRepository.findById(id).isPresent()) {
            taskRepository.deleteById(id);
            return new ResponseEntity<>(new ApiErrorDTO("200",
                    ResourceBundle.getBundle("lang", getLocale()).getString("ok1"),
                    ResourceBundle.getBundle("lang", getLocale()).getString("ok2"))
                    .toSuccess(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiErrorDTO("500",
                    ResourceBundle.getBundle("lang", getLocale()).getString("err"),
                    ResourceBundle.getBundle("lang", getLocale()).getString("err2"))
                    .toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static Locale getLocale() {
        if (SecurityContextHolder.getContext().getAuthentication() != null)
            return (!SecurityContextHolder.getContext().getAuthentication().getName().equals("user")) ?
                    Locale.FRANCE :
                    Locale.GERMANY;
        else {
            return Locale.GERMANY;
        }
    }
}
