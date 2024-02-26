package org.example;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Object[]> customers = new ArrayList<>();
        String[] brands = {"Золотые часы", "Часы FixPrice", "Часы на каждый день"};
        String[][] brandsToMarks = {{brands[0], "Золотой слиток"}, {brands[1], "Часы"}, {brands[2], "Повседнев"}};

        inputCustomersOrders(scanner, customers, brandsToMarks);
        removeDuplicates(customers);
        displayCustomersOrders(customers);
    }

    // Заполнение массива с покупателями
    public static void inputCustomersOrders(Scanner scanner, ArrayList<Object[]> customers, String[][] brandsToMarks) {
        for (int i = 0; i < 3; i++) { // Создаем не менее 3 заказов
            System.out.println("Введите данные о покупателе " + (i + 1) + ":");
            System.out.print("ФИО: ");
            String name = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Номер телефона: ");
            String phone = scanner.nextLine();
            System.out.println("Выберите позицию товара:");
            display(brandsToMarks);
            int choice = scanner.nextInt();
            scanner.nextLine(); // очистка буфера
            System.out.print("Введите количество: ");
            int amount = scanner.nextInt();
            scanner.nextLine(); // очистка буфера

            // Проверка на количество товаров > 0
            if (amount <= 0) {
                System.out.println("Количество товаров должно быть больше 0. Пожалуйста, введите корректное количество.");
                i--; // Чтобы повторно запросить данные о покупателе
                continue;
            }

            Object[] order = {name, email, phone, brandsToMarks[choice - 1], amount};
            customers.add(order);
        }
    }

    // Удаление дублирующихся заказов
    // Метод для удаления дублирующихся заказов
    public static void removeDuplicates(ArrayList<Object[]> orders) {
        orders.stream().distinct().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }


    // Вывод данных каждого заказчика и каждого заказа
    public static void displayCustomersOrders(ArrayList<Object[]> customers) {
        for (int i = 0; i < customers.size(); i++) {
            Object[] order = customers.get(i);
            System.out.println("\nДанные покупателя " + (i + 1) + ":");
            System.out.println("ФИО: " + order[0]);
            System.out.println("Email: " + order[1]);
            System.out.println("Номер телефона: " + order[2]);
            System.out.println("Позиция товара: " + ((String[]) order[3])[0] + " - " + ((String[]) order[3])[1]);
            System.out.println("Количество: " + order[4]);
        }
    }

    // Вывод доступных товаров
    public static void display(String[][] brandsToMarks) {
        for (int i = 0; i < brandsToMarks.length; i++) {
            System.out.println((i + 1) + ". " + brandsToMarks[i][0] + " - " + brandsToMarks[i][1]);
        }
    }
}
