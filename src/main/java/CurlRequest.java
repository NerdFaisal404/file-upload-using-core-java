import com.sun.deploy.util.StringUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurlRequest {
    public static void main(String[] args) throws IOException {
//        String command =
//                "curl -c logincookie.txt --data 'username=geneakin_5bb&password=0f23aae2' https://portal.proxyguys.com/login";
//        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
//        processBuilder.directory(new File("/Users/faisalahmed/Desktop/"));
//        Process process = processBuilder.start();
//        InputStream inputStream = process.getInputStream();
//
//        System.out.println( IOUtils.toString( inputStream , "UTF-8") );

        try (InputStream in = new FileInputStream("/Users/faisalahmed/Desktop/logincookie.txt")) {
            System.out.println(IOUtils.toString(in, "UTF-8"));
            String cookieValue = IOUtils.toString(in, "UTF-8");

            URL url = new URL("https://portal.proxyguys.com/api/v2/proxies/connect/geneakin_5bb/San Diego, CA");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Cookie",
                    "session=79558b8e-6a52-4e83-a3f7-180c3d8539c1.kvU1QsFYV3aZo1zoZdhYH1vShzE");
            int status = con.getResponseCode();

            System.out.println(IOUtils.toString(con.getInputStream(), "UTF-8"));
            con.disconnect();
        }
    }
}
