package WorkFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadFile {

    public static double[][] readMatrix(String fileName) {

        double[][] matrix = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            List<String> lines = new ArrayList<>();

            String s;
            while ((s = br.readLine()) != null) {
                lines.add(s);

            }
            int matrixWidth = lines.get(0).split("\\s+").length;
            int matrixHeight = lines.size();
            matrix = new double[matrixHeight][matrixWidth];


            for (int i = 0; i < matrixHeight; i++) {
                String str = lines.get(i).replaceAll(",", ".");
                String[] line = str.split("\\s+");
                matrix[i] = Arrays.stream(line).mapToDouble(Double::parseDouble).toArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matrix;
    }

    public static double[] readMas(String fileName) {

        double[] mas = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            List<String> lines = new ArrayList<>();

            String s;
            while ((s = br.readLine()) != null) {
                lines.add(s);
            }
            int masHeight = lines.size();
            mas = new double[masHeight];

            for (int i = 0; i < masHeight; i++) {
                String str = lines.get(i).replaceAll(",", ".");
                mas[i] = Double.parseDouble(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mas;
    }

    public static boolean checkFile(String fileName) {
        boolean resultChecked = false;

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            String s;
            while ((s = br.readLine()) != null) {
                if (s.split("\\s+").length > 1) {
                    resultChecked = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultChecked;
    }
}
