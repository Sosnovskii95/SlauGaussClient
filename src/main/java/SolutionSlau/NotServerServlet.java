package SolutionSlau;

import GaussSolution.Gauss;
import WorkFile.ReadFile;
import WorkFile.WriteFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class NotServerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> filesPath = (List<String>) request.getAttribute("filesPath");
        String filePath = getServletContext().getInitParameter("file-upload") + "find_mas_x.txt";
        Gauss gauss = null;

        if (ReadFile.checkFile(filesPath.get(0)))
        {
            gauss = new Gauss(ReadFile.readMatrix(filesPath.get(0)), ReadFile.readMas(filesPath.get(1)));
        }
        else {
            gauss = new Gauss(ReadFile.readMatrix(filesPath.get(1)), ReadFile.readMas(filesPath.get(0)));
        }
        if (gauss != null)
        {
            gauss.Solution();
            gauss.findMasX();
            WriteFile.writeMas(filePath, gauss.getMasX());
            request.setAttribute("pathName", filePath);
            request.getRequestDispatcher("/downloadFile").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
