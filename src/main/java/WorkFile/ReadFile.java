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

            while (br.ready()) {
                lines.add(br.readLine());
            }
            int matrixWidth = lines.get(0).split("\\s").length;
            int matrixHeight = lines.size();
            matrix = new double[matrixHeight][matrixWidth];

            for (int i = 0; i < matrixHeight; i++) {
                for (int j = 0; j < matrixWidth; j++) {
                    String str = lines.get(i).replaceAll(",",".");
                    String[] line = str.split("\\s");
                    matrix[i][j] = Double.parseDouble(line[j]);
                }
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }

        return matrix;
    }

    public static double[] readMas(String fileName) {

        double[] mas = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            List<String> lines = new ArrayList<>();

            while (br.ready()) {
                lines.add(br.readLine());
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
}
