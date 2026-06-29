package com.example.todo.controller;

import com.example.todo.entity.Category;
import com.example.todo.service.TaskService;
import com.example.todo.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    //タスクの表示
    @GetMapping("/tasks")
    public String tasks(@RequestParam(required = false)Category category, Model model) {
        if(category == null) {
            model.addAttribute("tasks", taskService.taskFindAll());
        }else {
            model.addAttribute("tasks", taskService.findByCategory(category));
        }
        return "tasks";
    }

    //タスク編集画面の表示
    @GetMapping("/tasks/{id}/edit")
    public String editTask(@PathVariable Long id, Model model) {
        Task task = taskService.findById(id);
        model.addAttribute("task", task);
        return "task-edit";
    }

    //タスク詳細画面の表示
    @GetMapping("/tasks/{id}")
    public String detailTask(@PathVariable Long id, Model model){
        Task task = taskService.findById(id);
        model.addAttribute("task", task);
        return "task-detail";
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

    //タスクの更新
    @PostMapping("/tasks/{id}/edit")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task task) {
        taskService.updateTask(id, task);
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
