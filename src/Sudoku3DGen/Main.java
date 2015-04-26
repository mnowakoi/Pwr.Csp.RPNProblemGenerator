package Sudoku3DGen;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Monis on 4/25/15.
 */
public class Main {
    public static void main(String[] args) {
        String outputPath = "/Users/Monis/IdeaProjects/Pwr.Csp.CspSolver/src/ProblemSamples/Sudoku3d.Txt";

        Scanner reader = new Scanner(System.in);
        System.out.println("Podaj liczbe bedaca kwadratem liczby naturalnej: ");
        int n = reader.nextInt();

        StringBuffer output = new StringBuffer();

        generateVariableSection(output, n);
        generateDomainSection(output, n);
        generateConstraints(output, n);

        System.out.println("Czy chcesz ustalic zmienna? y/n");
        String end = reader.next();
        while(end.equals("y")) {
            System.out.println("Podaj adres zmiennej w formacie liczbowym ŚRK np. 001");
            reader = new Scanner(System.in);
            String location = reader.next();
            System.out.println("Podaj wartość tego pola mniejszą od n i większą od 0: ");
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
        for(int w=0; w<6; w++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    output.append("P").append(w).append(i).append(j).append(" ");
                }
            }
        }
        output.append("\n");
    }

    private static void generateDomainSection(StringBuffer output, int n) {
        for(int w=0; w<6; w++) {
            for (int i = 0; i < n * n; i++) {
                for (int j = 0; j < n; j++) {
                    output.append(j).append(" ");
                }
                output.append("\n");
            }
        }
    }

    private static void generateConstraints(StringBuffer output, int n) {
        //rzedy
        for (int s = 0; s < 6; s++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    output.append("P").append(s).append(i).append(j).append(" ");
                }
                output.append(n).append(" ").append("rozne").append("\n");
            }
        }

        //kolumny
        for (int s = 0; s < 6; s++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    output.append("P").append(s).append(i).append(j).append(" ");
                }
                output.append(n).append(" ").append("rozne").append("\n");
            }
        }

        //mniejsze kwadraty
        int w = (int) Math.sqrt(n);
        for (int s = 0; s < 6; s++) {
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < w; y++) {
                    for (int i = 0; i < n / w; i++) {
                        for (int j = 0; j < n / w; j++) {
                            output.append("P").append(s).append((n / w) * x + j).append((n / w) * y + i).append(" ");
                        }
                    }
                    output.append(n).append(" ").append("rozne").append("\n");
                }
            }
        }

        //naprzeciwko siebie
        for (int s = 0; s < 3; s++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    output.append("P").append(2*s).append(i).append(j).append(" ").append("P").append(2*s+1).append(i).append(j).append(" ").append("<>").append("\n");
                }
            }
        }
    }
}


//rzedy
//        for (int w = 0; w < 6; w++) {
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    for (int k = j + 1; k < n; k++) {
//                        output.append("P").append(w).append(i).append(j).append(" ").append("P").append(w).append(i).append(k).append(" ").append("<>").append("\n");
//                    }
//                }
//            }
//        }
//
//        //kolumny
//        for (int w = 0; w < 6; w++) {
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    for (int k = j + 1; k < n; k++) {
//                        output.append("P").append(w).append(j).append(i).append(" ").append("P").append(w).append(k).append(i).append(" ").append("<>").append("\n");
//                    }
//                }
//            }
//        }
//
//        //mniejsze kwadraty
//        for (int w = 0; w < 6; w++) {
//            for (int x = 0; x < 3; x++) {
//                for (int y = 0; y < 3; y++) {
//                    for (int i = 0; i < n / 3; i++) {
//                        for (int j = 0; j < n / 3; j++) {
//                            output.append("P").append(w).append((n / 3) * x + j).append((n / 3) * y + i).append(" ");
//                        }
//                    }
//                    output.append((n * n) / 9).append(" ").append("rozne").append("\n");
//                }
//            }
//        }