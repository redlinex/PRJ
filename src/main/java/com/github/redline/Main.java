package com.github.redline;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {

        validateArgs(args);

        BigIntegerSolution solution = new BigIntegerSolution();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(args[1]))) {

            String s;
            do {
                try {
                    s = bufferedReader.readLine();
                    solution.nextIteration(s);
                } catch (IOException e) {
                    bufferedWriter.write(solution.getResult());
                    throw new IOException();
                }
            } while (s != null);
            bufferedWriter.write(solution.getResult());

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Произошла ошибка ввода/вывода, \n" +
                    "дальнейшее продолжение невозможно, информация обработана частично.");
        }
    }

    private static void validateArgs(String[] args) {
        if (args.length != 2) {
            System.err.println("Не корректно заданы входные данные");
            System.exit(1);
        }

        Path inputFile = Paths.get(args[0]);
        if (!Files.exists(inputFile) || inputFile.toAbsolutePath().toFile().length() == 0) {
            String msg = String.format("Файл не найден или пуст: %s", inputFile.toAbsolutePath().toString());
            System.err.println(msg);
            System.exit(1);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]))) {
            writer.write("");
        } catch (IOException e) {
            String msg = String.format("Не удается создать выводной файл: %s", args[1]);
            System.err.println(msg);
            System.exit(1);
        }
    }
}
