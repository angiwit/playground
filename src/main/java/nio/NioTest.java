package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

public class NioTest {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverChannel = null;
        final String HOSTNAME = "127.0.0.1";
        final int PORT = 8511;

// This is how you open a ServerSocketChannel
        serverChannel = ServerSocketChannel.open();
// You MUST configure as non-blocking or else you cannot register the serverChannel to the Selector.
        serverChannel.configureBlocking(false);
// bind to the address that you will use to Serve.
        serverChannel.socket().bind(new InetSocketAddress(HOSTNAME, PORT));

// This is how you open a Selector
        Selector selector = Selector.open();
        /*
         * Here you are registering the serverSocketChannel to accept connection, thus the OP_ACCEPT.
         * This means that you just told your selector that this channel will be used to accept connections.
         * We can change this operation later to read/write, more on this later.
         */
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        // Run the server as long as the thread is not interrupted.
        while (!Thread.currentThread().isInterrupted()) {
            /*
             * selector.select(TIMEOUT) is waiting for an OPERATION to be ready and is a blocking call.
             * For example, if a client connects right this second, then it will break from the select()
             * call and run the code below it. The TIMEOUT is not needed, but its just so it doesn't
             * block undefinable.
             */
            selector.select(2000);

            /*
             * If we are here, it is because an operation happened (or the TIMEOUT expired).
             * We need to get the SelectionKeys from the selector to see what operations are available.
             * We use an iterator for this.
             */
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();

            /**
             * Can be refactored to multiple reactors.
             */
            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                // remove the key so that we don't process this OPERATION again.
                keys.remove();

                // key could be invalid if for example, the client closed the connection.
                if (!key.isValid()) {
                    continue;
                }
                /*
                 * In the server, we start by listening to the OP_ACCEPT when we register with the Selector.
                 * If the key from the keyset is Acceptable, then we must get ready to accept the client
                 * connection and do something with it. Go read the comments in the accept method.
                 */
                if (key.isAcceptable()) {
                    System.out.println("Accepting connection");
                    accept(key);
                }
                /*
                 * If you already read the comments in the accept() method, then you know we changed
                 * the OPERATION to OP_WRITE. This means that one of these keys in the iterator will return
                 * a channel that is writable (key.isWritable()). The write() method will explain further.
                 */
                if (key.isWritable()) {
                    System.out.println("Writing...");
                    write(key);
                }
                /*
                 * If you already read the comments in the write method then you understand that we registered
                 * the OPERATION OP_READ. That means that on the next Selector.select(), there is probably a key
                 * that is ready to read (key.isReadable()). The read() method will explain further.
                 */
                if (key.isReadable()) {
                    System.out.println("Reading connection");
                    read(key);
                }
            }
        }
    }

    private static void read(SelectionKey key) {

    }

    private static void write(SelectionKey key) {

    }

    private static void accept(SelectionKey key) {

    }
}
