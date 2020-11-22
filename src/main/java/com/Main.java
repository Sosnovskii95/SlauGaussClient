package com;

import java.util.Scanner;
import WorkFile.ReadFile;
import WorkFile.WriteFile;

public class Main {
    public static void main(String[] args) {
        WriteFile writeFile = new WriteFile();

        double[] mas = {1,2,3,5};

        writeFile.writeMas("E:\\mas1.txt", mas);
    }
}
