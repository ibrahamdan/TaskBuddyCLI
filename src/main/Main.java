package main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Welcome to Task Buddy, buddy!");
        System.out.println("Type 'help' to see available commands.");

        while (true) {
            System.out.print("> ");
            command = scanner.nextLine().trim();

            switch (command.split(" ")[0].toLowerCase()) {
                case "add":
                    System.out.print("Enter task name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter priority (low, medium, high): ");
                    String priority = scanner.nextLine();
                    System.out.print("Enter deadline (YYYY-MM-DD): ");
                    String deadline = scanner.nextLine();
                    taskManager.addTask(name, priority, deadline);
                    break;
                case "list":
                    taskManager.listTasks();
                    break;
                case "complete":
                    System.out.print("Enter task ID to mark as complete: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    taskManager.completeTask(id);
                    break;
                case "delete":
                    System.out.print("Enter task ID to delete: ");
                    id = Integer.parseInt(scanner.nextLine());
                    taskManager.deleteTask(id);
                    break;
                case "summary":
                    taskManager.showSummary();
                    break;
                case "clear":
                    taskManager.clearTasks();
                    break;
                case "exit":
                    System.out.println("See ya, buddy!");
                    System.exit(0);
                    break;
                case "help":
                    System.out.println("Available command: add, list, complete, delete, summary, clear, and exit.");
                    break;
                default:
                    System.out.println("Unknown command. Type 'help' to see available commands.");
            }
        }
    }
}