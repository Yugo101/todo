package com.example.todo.service;

import java.util.List;
import java.util.Optional;

import com.example.todo.entity.Task;
import com.example.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //タスク全件取得
    public List<Task> taskFindAll() {
        return taskRepository.findAll();
    }

    //タスク作成
    public void createTask(Task task) {
        taskRepository.save(task);
    }

    //タスク削除
    public void deleteTask(long id) {
        taskRepository.deleteById(id);
    }

    //タスク完了
    public void completeTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("タスクが見つかりません"));

        task.setCompleted(true);
        taskRepository.save(task);
    }
}
