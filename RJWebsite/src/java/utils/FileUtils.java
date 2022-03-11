/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;
import static javax.xml.transform.OutputKeys.ENCODING;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;

/**
 *
 * @author Admin
 */
public class FileUtils {

    public String extractFileName(Part part) {
        // form-data; name="file"; filename="C:\file1.zip"
        // form-data; name="file"; filename="C:\Note\file2.zip"
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                // C:\file1.zip
                // C:\Note\file2.zip
                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                clientFileName = clientFileName.replace("\\", "/");
                int i = clientFileName.lastIndexOf('/');
                // file1.zip
                // file2.zip
                return clientFileName.substring(i + 1);
            }
        }
        return null;
    }

    public String inputStreamToBase64(InputStream is, long fileSize) throws IOException {
        byte[] imageBytes = new byte[(int) fileSize];
        is.read(imageBytes, 0, imageBytes.length);
        is.close();
        String encoded = Base64.getEncoder().encodeToString(imageBytes);
        return encoded;
    }

}
