package com.company;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String outputPath = "/Users/Monis/IdeaProjects/Pwr.Csp.CspSolver/src/ProblemSamples/Hetmany.Txt";

        Scanner reader = new Scanner(System.in);
        System.out.println("N: ");
        int n = reader.nextInt();

        StringBuffer output = new StringBuffer();

        generateVariableSection(output, n);
        generateDomainSection(output, n);
        generateConstraints(output, n);

        System.out.println("Writing to: " + outputPath);
        try {
            PrintWriter fileOut = new PrintWriter(outputPath);
            fileOut.println(output.toString());
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void generateConstraints(StringBuffer output, int n) {
        for(int i = 0;i < n;i++) {
            for(int j = i+1;j < n;j++) {
                output.append("H").append(i).append(" ").append("H").append(j).append(" <> ").append('\n');
            }
        }

        for(int i = 0;i < n;i++) {
            for(int j = i+1;j < n;j++) {
                output.append("H").append(i).append(" ").append("H").append(j).append(" - ").append("|| ");
                output.append(i).append(" ").append(j).append(" - ").append("||");
                output.append(" <> ").append('\n');
            }
        }
    }

    private static void generateDomainSection(StringBuffer output, int n) {
        for(int i = 0;i < n;i++) {
            for(int j = 0;j < n;j++) {
                output.append(j).append(" ");
            }
            output.append("\n");
        }
    }

    private static void generateVariableSection(StringBuffer output, int n) {
        for(int i = 0;i < n;i++) {
            output.append("H").append(i).append(" ");
        }
        output.append("\n");
    }
}
