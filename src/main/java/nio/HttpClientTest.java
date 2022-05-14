package nio;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpResponse<String> response = HttpClient
                .newBuilder()
                .proxy(ProxySelector.getDefault())
                .build()
                .send(HttpRequest.newBuilder().build(), HttpResponse.BodyHandlers.ofString());
    }
}
