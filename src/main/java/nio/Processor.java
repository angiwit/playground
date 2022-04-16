package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The difficulty here is to build a complete message, since reading is non-blocking, so read once can not make
 * sure all messages are arrived.
 * For big messages, there'll be too many times blocking select, this approach is no better than a blocking approach.
 * Since both are blocking and this is much more complicated.
 */
public class Processor {
    private static final ExecutorService service =
            Executors.newFixedThreadPool(2 * Runtime.getRuntime().availableProcessors());

    private Selector selector;

    public Processor() throws IOException {
        this.selector = SelectorProvider.provider().openSelector();
        start();
    }

    public void addChannel(SocketChannel socketChannel) throws ClosedChannelException {
        socketChannel.register(this.selector, SelectionKey.OP_READ);
    }

    public void wakeup() {
        this.selector.wakeup();
    }

    public void start() {
        service.submit(() -> {
            while (true) {
                if (selector.select(500) <= 0) {
                    continue;
                }
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove(); // remove the iterator, otherwise, the while loop will run forever and read 0.
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    if (key.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        int count = socketChannel.read(buffer);
                        if (count < 0) {
                            socketChannel.close();
                            key.cancel();
//                            LOGGER.info("{}\t Read ended", socketChannel);
                            continue;
                        } else if (count == 0) {
//                            LOGGER.info("{}\t Message size is 0", socketChannel);
                            continue;
                        } else {
//                            LOGGER.info("{}\t Read message {}", socketChannel, new String(buffer.array()));
                        }
                    }
                    if (key.isWritable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        buffer.flip();
                        socketChannel.write(buffer);
                        buffer.clear();
                    }
                }
            }
        });
    }
}