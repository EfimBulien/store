package org.example;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Создаем сканер для ввода данных с консоли
        Scanner scanner = new Scanner(System.in);
        // Создаем список для хранения заказов покупателей
        ArrayList<Object[]> customers = new ArrayList<>();
        // Массив с наименованиями брендов и их марками
        String[] brands = {"Золотые часы", "Часы FixPrice", "Часы на каждый день"};
        String[][] brandsToMarks = {{brands[0], "Золотой слиток"}, {brands[1], "Часы"}, {brands[2], "Повседнев"}};

        // Запускаем метод для заполнения данных о заказах покупателей
        inputCustomersOrders(scanner, customers, brandsToMarks);
        // Удаляем дублирующиеся заказы
        removeDuplicates(customers);
        // Выводим информацию о заказах покупателей
        displayCustomersOrders(customers);
    }

    // Метод для заполнения данных о заказах покупателей
    public static void inputCustomersOrders(Scanner scanner, ArrayList<Object[]> customers, String[][] brandsToMarks) {
        for (int i = 0; i < 3; i++) { // Создаем не менее 3 заказов
            // Вводим данные о покупателе
            System.out.println("Введите данные о покупателе " + (i + 1) + ":");
            System.out.print("ФИО: ");
            String name = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Номер телефона: ");
            String phone = scanner.nextLine();
            System.out.println("Выберите позицию товара:");
            // Выводим доступные товары
            display(brandsToMarks);
            int choice = scanner.nextInt();
            scanner.nextLine(); // очистка буфера
            System.out.print("Введите количество: ");
            int amount = scanner.nextInt();
            scanner.nextLine(); // очистка буфера

            // Проверка на количество товаров > 0
            if (amount <= 0) {
                System.out.println("Количество товаров должно быть не менее 1.");
                i--; // Чтобы повторно запросить данные о покупателе
                continue;
            }

            // Создаем заказ и добавляем его в список заказов покупателей
            Object[] order = {name, email, phone, brandsToMarks[choice - 1], amount};
            customers.add(order);
        }
    }

    // Метод для удаления дублирующихся заказов
    public static void removeDuplicates(ArrayList<Object[]> orders) {
        // Используем поток для удаления дубликатов заказов
        orders.stream().distinct().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    // Метод для вывода данных каждого заказчика и каждого заказа
    public static void displayCustomersOrders(ArrayList<Object[]> customers) {
        // Перебираем заказы каждого покупателя и выводим информацию о них
        for (int i = 0; i < customers.size(); i++) {
            Object[] order = customers.get(i);
            System.out.println("\nДанные покупателя " + (i + 1) + ": " +
                    "\nФИО: "+ order[0] + "\nEmail: " + order[1] + "\nНомер телефона: " + order[2] +
                    "\nПозиция товара: " + ((String[]) order[3])[0] + " - " + ((String[]) order[3])[1] +
                    "\nКоличество: " + order[4]);
        }
    }

    // Метод для вывода доступных товаров
    public static void display(String[][] brandsToMarks) {
        // Выводим список доступных товаров с их наименованиями и марками
        for (int i = 0; i < brandsToMarks.length; i++) {
            System.out.println((i + 1) + ". " + brandsToMarks[i][0] + " - " + brandsToMarks[i][1]);
        }
    }
}
