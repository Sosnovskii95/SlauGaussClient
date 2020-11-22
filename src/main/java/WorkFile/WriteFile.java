package WorkFile;

import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {

    public boolean writeMas(String fileName, double[] mas) {
        boolean result = true;

        try {
            FileWriter writer = new FileWriter(fileName, false);

            for (double i: mas
                 ) {
                writer.write(String.valueOf(i)+'\n');
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }
}
