package mmap;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * sendfile sys call is specifically for data sending to a socket, if you need to write data to a file,
 * you need to use splice or use a customized buffer to copy data to the buffer and then write to file.
 * In java, this function is encapsulated by transferTo, if target is socket, it uses syscall sendfile.
 * if target is file, then it uses a directbytebuffer(8k) to copy data to file.
 * refer: https://stackoverflow.com/questions/7463689/most-efficient-way-to-copy-a-file-in-linux
 */
public class Sendfile {

    static int length = 0x8FFFFFF; // 128 Mb

    public static void main(String[] args) throws Exception {
        FileChannel fc = new RandomAccessFile("test.dat", "rw").getChannel();
        MappedByteBuffer out = fc.map(FileChannel.MapMode.READ_WRITE, 0, length);
        for (int i = 0; i < length; i++)
            out.put((byte) 'x');
        System.out.println("Finished writing");
        FileChannel to = new RandomAccessFile("random.dat", "rw").getChannel();
        fc.transferTo(0, fc.size(), to);
    }
}
