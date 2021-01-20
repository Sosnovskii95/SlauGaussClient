package SolutionSlau;

import GaussSolution.Gauss;
import GaussSolution.GaussNetwork;
import WorkFile.ReadFile;
import WorkFile.WriteFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ServerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> filesPath = (List<String>) request.getAttribute("filesPath");
        List<String> serversInfo = (List<String>) request.getAttribute("serversInfo");
        String filePath = getServletContext().getInitParameter("file-upload") + "find_mas_x.txt";
        GaussNetwork gaussNetwork = null;
        Gauss gauss = null;

        if (ReadFile.checkFile(filesPath.get(0))) {
            gauss = new Gauss(ReadFile.readMatrix(filesPath.get(0)), ReadFile.readMas(filesPath.get(1)));
        } else {
            gauss = new Gauss(ReadFile.readMatrix(filesPath.get(1)), ReadFile.readMas(filesPath.get(0)));
        }
        if (gauss != null) {
            long startTime = System.nanoTime();
            gauss.Solution();
            gaussNetwork = new GaussNetwork(gauss.getMatrixA(), gauss.getMasB(), serversInfo);
            gaussNetwork.Solution();
            long endTime = System.nanoTime() - startTime;
            WriteFile.writeMas(filePath, gaussNetwork.getMasX());
            request.setAttribute("pathName", filePath);
            request.setAttribute("time", endTime);
            request.setAttribute("mode", "Многопоточный");
            request.getRequestDispatcher("/solution.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
