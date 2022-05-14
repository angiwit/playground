package nio.asynchttpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.nio.reactor.ConnectingIOReactor;

import java.util.concurrent.CountDownLatch;

public class ConnectingIOReactorTest {
    public static void main(String[] args) throws Exception {
        ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor();
        PoolingNHttpClientConnectionManager cm = new PoolingNHttpClientConnectionManager(ioReactor);
        cm.setMaxTotal(100);
        CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClients.custom().setConnectionManager(cm).build();
        httpAsyncClient.start();
        String[] urisToGet = {
                "http://www.chinaso.com/",
                "http://www.so.com/",
                "http://www.qq.com/",
        };

        final CountDownLatch latch = new CountDownLatch(urisToGet.length);
        for (final String uri : urisToGet) {
            final HttpGet httpget = new HttpGet(uri);
            httpAsyncClient.execute(httpget, new FutureCallback<HttpResponse>() {

                public void completed(final HttpResponse response) {
                    latch.countDown();
                    System.out.println(httpget.getRequestLine() + "->" +
                            response.getStatusLine());
                }

                public void failed(final Exception ex) {
                    latch.countDown();
                    System.out.println(httpget.getRequestLine() + "->" +
                            ex);
                }

                public void cancelled() {
                    latch.countDown();
                    System.out.println(httpget.getRequestLine() + " cancelled");
                }

            });
        }
        latch.await();
    }
}
