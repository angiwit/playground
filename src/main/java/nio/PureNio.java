package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashSet;
import java.util.Set;

public class PureNio {

    private static final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    private static final Set<SocketChannel> socketChannels = new HashSet<>();

    public static void test() {
        ServerSocketChannel ssc = null;
        try {
            ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress(8080));
            ssc.configureBlocking(false);
            while (true) {
                SocketChannel sc = ssc.accept();
                if (sc != null) {
                    sc.configureBlocking(false);
                    socketChannels.add(sc);
                }
                /**
                 * If we do NOT use Selector, then we need this approach to make sure no blocking when read.
                 */
                for (SocketChannel socketChannel : socketChannels) {
                    int length = socketChannel.read(byteBuffer);
                    if (length == -1) {
                        sc.close();
                        String received = new String(byteBuffer.array(), 0, length);
                        System.out.println(received);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
