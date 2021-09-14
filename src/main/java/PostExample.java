import java.io.File;
import java.io.IOException;

import okhttp3.*;

public class PostExample {
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    private final OkHttpClient client = new OkHttpClient();

    public void run() throws Exception {
        String jsonPayload = "{\"username\":\"faisal\",\"call_time\":\"40\"}";
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("jsonPayload", jsonPayload)
                .addFormDataPart("files", "logo-square.png",
                        RequestBody.create(MEDIA_TYPE_PNG, new File("/Users/faisalahmed/Desktop/Faisal_Ahmed.jpeg")))
                .build();

        Request request = new Request.Builder()
                .url("https://appiumapi.herokuapp.com/appium/uploadFiles")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println(response.body().string());
        }
    }

    public static void main(String[] args) throws Exception {
        PostExample example = new PostExample();
        example.run();
        System.out.println("hello");
    }
}
