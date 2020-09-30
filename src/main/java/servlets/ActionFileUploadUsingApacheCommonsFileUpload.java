package servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/action_file_upload-apache")
public class ActionFileUploadUsingApacheCommonsFileUpload extends HttpServlet {
    static String UPLOAD_DIRECTORY = "upload";
    static int MAX_FILE_SIZE = 1024 * 1024 * 5;
    static int MEMORY_THRESHOLD = 1024 * 1024;
    static int MAX_REQUEST_SIZE = 1024 * 1024 * 5 * 5;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (ServletFileUpload.isMultipartContent(req)) {

                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setSizeThreshold(MEMORY_THRESHOLD);
                factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setFileSizeMax(MAX_FILE_SIZE);
                upload.setSizeMax(MAX_REQUEST_SIZE);
                String uploadPath = getServletContext().getRealPath("")
                        + File.separator + UPLOAD_DIRECTORY;
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                if (ServletFileUpload.isMultipartContent(req)) {
                    //...
                    List<FileItem> formItems = upload.parseRequest(req);
                    if (formItems != null && formItems.size() > 0) {
                        for (FileItem item : formItems) {
                            if (!item.isFormField()) {
                                String fileName = new File(item.getName()).getName();
                                String filePath = uploadPath + File.separator + fileName;
                                File storeFile = new File(filePath);
                                item.write(storeFile);
                                req.setAttribute("message", "File "
                                        + fileName + " has uploaded successfully!");
                            }
                        }
                    }
                }
                //...
            }
        } catch (Exception exception) {
            req.setAttribute("message", "Exception " + exception.getLocalizedMessage());
        }
        req.getRequestDispatcher("/").forward(req, resp);

    }
}
