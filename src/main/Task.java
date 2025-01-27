package main;

public class Task {
    private static int idCounter = 1;
    private final int id;
    private final String name;
    private final String priority;
    private final String deadline;
    private boolean isCompleted;

    public Task(String name, String priority, String deadline) {
        this.id = idCounter++;
        this.name = name;
        this.priority = priority;
        this.deadline = deadline;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPriority() {
        return priority;
    }

    public String getDeadline() {
        return deadline;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    public String toString() {
        return String.format("ID: %s | Task: %s | Priority: %s | Deadline: %s | Completed: %s", id, name, priority, deadline, isCompleted? "Yes" : "No");
    }

    public static void resetCounter() {
        idCounter = 1;
    }
}
