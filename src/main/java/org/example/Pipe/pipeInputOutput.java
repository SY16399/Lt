package org.example.Pipe;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class pipeInputOutput {
    static class WriteData {
        public void writeMethod(PipedOutputStream out) {
            try {
                System.out.println("write :");
                for (int i = 0; i < 300; i++) {
                    String outData = "" + (i + 1);
                    out.write(outData.getBytes());
                    System.out.print(outData);
                }
                System.out.println();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    static class ReadData{
        public void readMethod(PipedInputStream input){
            try{
                System.out.println("read    :");
                byte[] byteArrary = new byte[20];
                int readLength = input.read(byteArrary);// 将读取的字节放入数组中,返回读取的长度。如果没有写入呢将会阻塞。
                while(readLength != -1){
                    String newData = new String(byteArrary,0,readLength);
                    System.out.print(newData);
                    readLength = input.read(byteArrary);
                }
                System.out.println();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    static class ThreadWrite extends Thread{
        private WriteData write;
        private PipedOutputStream out;

        public ThreadWrite(WriteData write, PipedOutputStream out) {
            super();
            this.write = write;
            this.out = out;
        }

        @Override
        public void run() {
            write.writeMethod(out);
        }
    }
    static class ThreadRead extends Thread{
        private ReadData read;
        private PipedInputStream input;

        public ThreadRead(ReadData read, PipedInputStream input) {
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
            PipedInputStream inputStream = new PipedInputStream();
            PipedOutputStream outputStream = new PipedOutputStream();
            inputStream.connect(outputStream);
            //outputStream.connect(inputStream);
            ThreadRead threadRead = new ThreadRead(readData,inputStream);
            threadRead.start();
            Thread.sleep(2000);
            ThreadWrite threadWrite = new ThreadWrite(writeData,outputStream);
            threadWrite.start();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
