package UploadDownloadFile;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.server.ExportException;

public class DownloadFileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        File file = new File(request.getParameter("pathName"));
        ServletOutputStream outputStream = null;
        BufferedInputStream inputStream = null;
        try {
            outputStream = response.getOutputStream();//Получаем поток для отправки из сервлета
            response.setContentType("application/vnd.ms-excel");//Создаем тип того, что будем отправлять
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName() + "");//Создаем заголовок отправки
            response.setContentLength((int) file.length());
            FileInputStream fileInputStream = new FileInputStream(file);//Переносим содержимое файла в потом
            inputStream = new BufferedInputStream(fileInputStream);
            int readBytes = 0;
            while ((readBytes = inputStream.read()) != -1)//Читаем файл
                outputStream.write(readBytes);//Записываем в поток из сервлета
        }catch (ExportException e){
            e.printStackTrace();
        }finally {
            //Закрываем потоки
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }
        //Перенаправление браузера на страницу отчетов
        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
