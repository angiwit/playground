package nio.asynchttpclient;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class SimpleTestWithMyCallback implements FutureCallback<HttpResponse> {

    public void failed(final Exception ex) {
        System.out.println(ex.getLocalizedMessage());
    }

    public void completed(final HttpResponse response) {
        try {
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cancelled() {
        System.out.println("cancelled");
    }

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        // 2.创建异步httpclient对象
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom().build();

        // 3.发起调用
        try {

            // 3.0启动
            httpclient.start();
            // 3.1请求参数
            HttpGet httpget1 = new HttpGet("http://127.0.0.1:8080/test1");
            HttpGet httpget2 = new HttpGet("http://127.0.0.1:8080/test2");
            // 3.2发起请求，不阻塞，马上返回
            httpclient.execute(httpget1, new SimpleTestWithMyCallback());
            httpclient.execute(httpget2, new SimpleTestWithMyCallback());

            // 3.3休眠10s,避免请求执行完成前，关闭了链接
            Thread.sleep(10000);
        } finally {
            httpclient.close();
        }
    }
}
