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
            System.err.println("Input/Output Exception happened \n" +
                    "impossible to continue, input was not fully processed.");
        }
    }

    private static void validateArgs(String[] args) {
        if (args.length != 2) {
            System.err.println("Invalid input data.");
            System.exit(1);
        }

        Path inputFile = Paths.get(args[0]);
        if (!Files.exists(inputFile) || inputFile.toAbsolutePath().toFile().length() == 0) {
            String msg = String.format("File is either not found or empty: %s", inputFile.toAbsolutePath().toString());
            System.err.println(msg);
            System.exit(1);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]))) {
            writer.write("");
        } catch (IOException e) {
            String msg = String.format("Unable to create output file: %s", args[1]);
            System.err.println(msg);
            System.exit(1);
        }
    }
}
