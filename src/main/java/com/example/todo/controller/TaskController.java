package com.example.todo.controller;

import com.example.todo.service.TaskService;
import com.example.todo.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    //タスクの一覧表示
    @GetMapping("/tasks")
    public String tasks(Model model) {
        model.addAttribute("tasks", taskService.taskFindAll());
        return "tasks";
    }

    //タスク作成画面の表示
    @GetMapping("/tasks/new")
    public String newTask(Model model) {
        model.addAttribute("task", new Task());
        return "task-create";
    }

    //タスクの保存
    @PostMapping("/tasks")
    public String createTask(@ModelAttribute Task task) {
        taskService.createTask(task);
        return "redirect:/tasks";
    }

    //タスクの削除
    @PostMapping("/tasks/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    //タスクの完了
    @PostMapping("/tasks/{id}/complete")
    public String completeTask(@PathVariable Long id) {
        taskService.completeTask(id);
        return "redirect:/tasks";
    }
}
