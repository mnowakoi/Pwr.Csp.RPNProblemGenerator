package SudokuGen;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Monis on 4/25/15.
 */
public class Main {
    public static void main(String[] args) {
        String outputPath = "/Users/Monis/IdeaProjects/Pwr.Csp.CspSolver/src/ProblemSamples/Sudoku.Txt";

        Scanner reader = new Scanner(System.in);
        System.out.println("Podaj liczbe bedaca kwadratem liczby naturalnej N: ");
        int n = reader.nextInt();

        StringBuffer output = new StringBuffer();

        generateVariableSection(output, n);
        generateDomainSection(output, n);
        generateConstraints(output, n);

        System.out.println("Czy chcesz ustalic zmienna? y/n");
        String end = reader.next();
        while (end.equals("y")) {
            System.out.println("Podaj adres zmiennej w formacie liczbowym RK np. 01");
            reader = new Scanner(System.in);
            String location = reader.next();
            System.out.println("Podaj wartość tego pola mniejszą od N i większą od 0: ");
            n = reader.nextInt();
            output.append("P").append(location).append(" ").append(n).append(" ").append("=").append("\n");
            System.out.println("Czy chcesz kontynuowac? y/n");
            end = reader.next();
        }

        System.out.println("Writing to: " + outputPath);
        try {
            PrintWriter fileOut = new PrintWriter(outputPath);
            fileOut.println(output.toString());
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void generateVariableSection(StringBuffer output, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                output.append("P").append(i).append(j).append(" ");
            }
        }
        output.append("\n");
    }

    private static void generateDomainSection(StringBuffer output, int n) {
        for (int i = 0; i < n * n; i++) {
            for (int j = 0; j < n; j++) {
                output.append(j).append(" ");
            }
            output.append("\n");
        }
    }

    private static void generateConstraints(StringBuffer output, int n) {
        //rzedy
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                output.append("P").append(i).append(j).append(" ");
            }
            output.append(n).append(" ").append("rozne").append("\n");
        }

        //kolumny
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                output.append("P").append(j).append(i).append(" ");
            }
            output.append(n).append(" ").append("rozne").append("\n");
        }

        //mniejsze kwadraty
        int w = (int) Math.sqrt(n);
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < w; y++) {
                for (int i = 0; i < n / w; i++) {
                    for (int j = 0; j < n / w; j++) {
                        output.append("P").append((n / w) * x + j).append((n / w) * y + i).append(" ");
                    }
                }
                output.append(n).append(" ").append("rozne").append("\n");
            }
        }
    }
}



//        //rzedy
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                for (int k = j + 1; k < n; k++) {
//                    output.append("P").append(i).append(j).append(" ").append("P").append(i).append(k).append(" ").append("<>").append("\n");
//                }
//            }
//        }
//
//        //kolumny
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                for (int k = j + 1; k < n; k++) {
//                    output.append("P").append(j).append(i).append(" ").append("P").append(k).append(i).append(" ").append("<>").append("\n");
//                }
//            }
//        }