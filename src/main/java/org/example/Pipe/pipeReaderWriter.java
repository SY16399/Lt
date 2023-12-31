package org.example.Pipe;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;public class pipeReaderWriter {
    static class WriteData {
        public void writeMethod(PipedWriter out) {
            try {
                System.out.println("write :");
                for (int i = 0; i < 300; i++) {
                    String outData = "" + (i + 1);
                    out.write(outData);
                    System.out.print(outData);
                }
                System.out.println();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class ReadData {
        public void readMethod(PipedReader input) {
            try {
                System.out.println("read :");
                char[] byteArray = new char[20];
                int readLength = input.read(byteArray);
                while (readLength != -1) {
                    String newData = new String(byteArray, 0, readLength);
                    System.out.print(newData);
                    readLength = input.read(byteArray);
                }
                System.out.println();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadWriter extends Thread {
        private WriteData write;
        private PipedWriter out;

        public ThreadWriter(WriteData write, PipedWriter out) {
            super();
            this.write = write;
            this.out = out;
        }

        @Override
        public void run() {
            write.writeMethod(out);
        }
    }

    static class ThreadRead extends Thread {
        private ReadData read;
        private PipedReader input;

        public ThreadRead(ReadData read, PipedReader input) {
            super();
            this.read = read;
            this.input = input;
        }

        @Override
        public void run() {
            read.readMethod(input);
        }
    }

    public static void main(String[] args) {
        try {
            WriteData writeData = new WriteData();
            ReadData readData = new ReadData();
            PipedReader inputStream = new PipedReader();
            PipedWriter outputStream = new PipedWriter();
            inputStream.connect(outputStream);
            ThreadRead threadRead = new ThreadRead(readData, inputStream);
            threadRead.start();
            Thread.sleep(2000);
            ThreadWriter threadWriter = new ThreadWriter(writeData, outputStream);
            threadWriter.start();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}


