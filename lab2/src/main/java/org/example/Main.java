package org.example;
import java.util.Random;
import java.util.Scanner;



public class Main {
    private static final int MAX_SIZE = 20;
    private static final int MIN_RANDOM = 1;
    private static final int MAX_RANDOM = 100;

    private int[][] matrix;
    private int width;
    private int height;

    public Main(int width, int height) {
        if (width > MAX_SIZE || height > MAX_SIZE) {
            throw new IllegalArgumentException("Розмір матриці перевищує максимальну межу 20x20");
        }
        this.width = width;
        this.height = height;
        this.matrix = new int[width][height];
    }

    public void fillRandom() {
        Random random = new Random();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                matrix[i][j] = random.nextInt(MAX_RANDOM - MIN_RANDOM + 1) + MIN_RANDOM;
            }
        }
    }

    public void fillFromUserInput(Scanner scanner) {
        System.out.println("Введіть елементи матриці:");
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                System.out.printf("Елемент [%d][%d]: ", i, j);
                matrix[i][j] = scanner.nextInt();
            }
        }
    }

    public void findMinMaxAndAverage() {
        int min = matrix[0][0];
        int max = matrix[0][0];
        int sum = 0;
        int count = width * height;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int current = matrix[i][j];
                sum += current;
                if (current < min) {
                    min = current;
                }
                if (current > max) {
                    max = current;
                }
            }
        }

        double average = (double) sum / count;

        System.out.println("Мінімальний елемент: " + min);
        System.out.println("Максимальний елемент: " + max);
        System.out.println("Середній показник: " + average);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть ширину матриці (не більше 20): ");
        int width = scanner.nextInt();

        System.out.print("Введіть висоту матриці (не більше 20): ");
        int height = scanner.nextInt();

        Main matrixOps = new Main(width, height);

        System.out.print("Ви хочете заповнити матрицю випадковим чином? (+/-): ");
        String choice = scanner.next();

        if (choice.equalsIgnoreCase("+")) {
            matrixOps.fillRandom();
        } else {
            matrixOps.fillFromUserInput(scanner);
        }

        matrixOps.findMinMaxAndAverage();

        scanner.close();
    }
}
