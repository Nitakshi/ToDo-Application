package com.app.todoapp.service;

import com.app.todoapp.models.Task;
import com.app.todoapp.repository.Taskrepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//ALl the business logic resides here
@Service
public class TaskService {
    private final Taskrepository taskRepository;

    public TaskService(Taskrepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
         return taskRepository.findAll();
    }

    public void createTasks(String title) {
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);
        taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void toggleTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(()-> new RuntimeException("Task not found"));
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
    }
}
