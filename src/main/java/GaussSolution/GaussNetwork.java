package GaussSolution;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

public class GaussNetwork {
    private double[][] matrixA;
    private double[] masB;
    private List<String> serversInfo;
    private double[] masX;


    public GaussNetwork(double[][] matrixA, double[] masB, List<String> serversInfo) {
        this.matrixA = matrixA;
        this.masB = masB;
        this.serversInfo = serversInfo;
    }

    public double[] getMasX() {
        return masX;
    }

    public void Solution() {
        masX = new double[matrixA.length];
        Socket[] sockets = new Socket[serversInfo.size()];
        try {
            int k = matrixA.length;
            masX[k - 1] = (masB[k - 1] - 0.0) / matrixA[k - 1][k - 1];
            k -= 2;
            while (k >= 0) {
                for (int i = 0; i < serversInfo.size(); i++) {
                    if (k - i >= 0) {
                        String[] server = serversInfo.get(i).split(":");
                        sockets[i] = new Socket();
                        sockets[i].connect(new InetSocketAddress(server[0], Integer.parseInt(server[1])), 2000);

                        double[] temp = new double[matrixA.length];

                        for (int j = k - i; j < matrixA.length; j++)
                            temp[j] = masX[j];

                        String sendData = convertMas(matrixA[k - i]) + "mas;" + convertMas(temp) + (k - i);


                        OutputStream out = sockets[i].getOutputStream();
                        out.write(sendData.getBytes());
                        out.flush();

                        InputStream in = sockets[i].getInputStream();

                        byte[] data = new byte[1024];
                        int countByte = in.read(data);
                        if (countByte > 0) {
                            masX[k - i] = (masB[k - i] - Double.parseDouble(new String(data, 0, countByte))) / matrixA[k - i][k - i];
                        }

                        sockets[i].close();
                    }
                }
                k -= serversInfo.size();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String convertMas(double[] mas) {
        String result = "";

        for (double m : mas) {
            result += m + ";";
        }

        return result;
    }
}
