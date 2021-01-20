package UploadDownloadFile;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UploadFileServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean multiPart = ServletFileUpload.isMultipartContent(request);
        File file;
        String filePath = getServletContext().getInitParameter("file-upload");
        List<String> filesPath = new ArrayList<>();
        List<String> serversInfo = new ArrayList<>();
        String typeSolution = null;

        DiskFileItemFactory factory = new DiskFileItemFactory();

        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            List fileItems = upload.parseRequest(request);

            // Process the uploaded file items
            Iterator i = fileItems.iterator();

            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (!fi.isFormField()) {
                    // Get the uploaded file parameters
                    String fieldName = fi.getFieldName();
                    String fileName = fi.getName();
                    String contentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();

                    // Write the file
                    if (fileName.lastIndexOf("\\") >= 0) {
                        file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
                    filesPath.add(file.getPath());
                    fi.write(file);
                }
                else
                {
                    if (fi.getFieldName().equals("servers"))
                    {
                        String[] servers = fi.getString().split(";");
                        for (String str: servers
                             ) {
                            serversInfo.add(str);
                        }
                    }
                    if (fi.getFieldName().equals("mode"))
                    {
                        typeSolution = fi.getString();
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        request.setAttribute("filesPath", filesPath);
        if (typeSolution.equals("many"))
        {
            request.setAttribute("serversInfo", serversInfo);
            request.getRequestDispatcher("/server").forward(request, response);
        }
        else {
            request.getRequestDispatcher("/notServer").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
