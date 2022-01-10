import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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


        try {
            URL urlDisconnectProxy = new URL("https://portal.proxyguys.com/api/v2/proxies/disconnect/9688a94bd0604fdf98beaea5b2cbc96b");
            HttpURLConnection disconnectRequest = (HttpURLConnection) urlDisconnectProxy.openConnection();
            disconnectRequest.setRequestMethod("GET");
            disconnectRequest.setRequestProperty("Cookie",
                    "session=79558b8e-6a52-4e83-a3f7-180c3d8539c1.kvU1QsFYV3aZo1zoZdhYH1vShzE");
            int statusDisconnect = disconnectRequest.getResponseCode();

            String requestResponse = IOUtils.toString(disconnectRequest.getInputStream(), "UTF-8");
//            Gson g = new Gson();
//            ProxyAvailableResponse proxyAvailableResponse = g.fromJson(availableData, ProxyAvailableResponse.class);
//            System.out.printf("--- "+ proxyAvailableResponse.getResult().get(0).getId());

            System.out.println(IOUtils.toString(disconnectRequest.getInputStream(), "UTF-8"));
            disconnectRequest.disconnect();
        } catch (Exception e) {

        }


        URL url = new URL("https://portal.proxyguys.com/api/v2/proxies/availability");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Cookie",
                "session=79558b8e-6a52-4e83-a3f7-180c3d8539c1.kvU1QsFYV3aZo1zoZdhYH1vShzE");
        int status = con.getResponseCode();

        String availableData = IOUtils.toString(con.getInputStream(), "UTF-8");
        Gson g = new Gson();
        ProxyAvailableResponse proxyAvailableResponse = g.fromJson(availableData, ProxyAvailableResponse.class);
        System.out.printf("--- " + proxyAvailableResponse.getResult().get(0).getId());

        System.out.println(IOUtils.toString(con.getInputStream(), "UTF-8"));
        con.disconnect();

        List<ResultItem> result = new ArrayList<>();

        for (ResultItem item : proxyAvailableResponse.getResult()
        ) {
            if (item.getAvailable() == 1) {
                result.add(item);
            }
        }


        int rnd = new Random().nextInt(result.size());
        String randomValue = result.get(rnd).getId();

        System.out.println("Random Location ID = " + randomValue);

        URL urlChangeProxy = new URL("https://portal.proxyguys.com/api/v2/proxies/connect/9688a94bd0604fdf98beaea5b2cbc96b/" + randomValue);

        HttpURLConnection conectRequest = (HttpURLConnection) urlChangeProxy.openConnection();
        conectRequest.setRequestMethod("GET");
        conectRequest.setRequestProperty("Cookie",
                "session=79558b8e-6a52-4e83-a3f7-180c3d8539c1.kvU1QsFYV3aZo1zoZdhYH1vShzE");
        int statusConnectRequest = conectRequest.getResponseCode();

        String requestResponse = IOUtils.toString(conectRequest.getInputStream(), "UTF-8");
//            Gson g = new Gson();
//            ProxyAvailableResponse proxyAvailableResponse = g.fromJson(availableData, ProxyAvailableResponse.class);
//            System.out.printf("--- "+ proxyAvailableResponse.getResult().get(0).getId());

        System.out.println(requestResponse);
        conectRequest.disconnect();

    }
}
