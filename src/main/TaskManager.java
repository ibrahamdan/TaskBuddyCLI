package main;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final ArrayList<Task> tasks = new ArrayList<>();
    private static final String FILE_NAME  = "tasks.json";

    public TaskManager() {
        loadTasks();
        checkReminders();
    }

    public void addTask(String name, String priority, String deadline) {
        Task task = new Task(name, priority, deadline);
        tasks.add(task);
        System.out.println("Task added successfully!");
        saveTasks();
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (Task t : tasks) {
                System.out.println(t);
            }
        }
    }

    public void completeTask(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.markCompleted();
                System.out.println("Task " + id + " marked as completed!");
                saveTasks();
                return;
            }
        }
        System.out.println("Task not found.");
    }

    public void deleteTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
        System.out.println("Task " + id + " deleted successfully!");
        saveTasks();
    }

    private void saveTasks() {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            Gson gson = new Gson();
            gson.toJson(tasks, writer);
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }

    private void loadTasks() {
        try (Reader reader = new FileReader(FILE_NAME)) {
            Gson gson = new Gson();
            Type taskListType = new TypeToken<List<Task>>() {}.getType();
            List<Task> loadedTasks = gson.fromJson(reader, taskListType);
            if (loadedTasks != null) {
                tasks.addAll(loadedTasks);
            }
        } catch (FileNotFoundException ignored) {

        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }

    public void showSummary() {
        int totalTasks = tasks.size();
        long completedTasks = tasks.stream().filter(Task::isCompleted).count();
        long highPriority = tasks.stream().filter(task -> task.getPriority().equalsIgnoreCase("high")).count();

        System.out.printf("Total tasks: %d%n", totalTasks);
        System.out.printf("Completed: %d%n", completedTasks);
        System.out.printf("High priority: %d%n", highPriority);
    }

    private void checkReminders() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        for (Task task : tasks) {
            if (task.getDeadline().equals(tomorrow.toString()) && !task.isCompleted()) {
                System.out.println("Tasks due tomorrow:");
                break;
            }
        }
        for (Task task : tasks) {
            if (task.getDeadline().equals(tomorrow.toString()) && !task.isCompleted()) {
                System.out.println(task);
            }
        }
    }

    public void clearTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to clear.");
        } else {
            tasks.clear();
            saveTasks();
            System.out.println("All tasks have been cleared.");
        }
    }
}
