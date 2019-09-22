package cn.huihongcloud;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by 钟晖宏 on 2018/5/30
 */
public class ServerTest {


    public static void main(String[] args) {
        (new ServerTest()).connectTest();
    }
    public void connectTest() {
        String testStr = "1001 17.08.11   14:19:15 05    119.138151 26.049941 -22.8 3.760 82";
        System.out.println(testStr.length());
        int count = 5;
        while (count > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            -- count;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Socket socket = null;
            try {
                socket = new Socket("127.0.0.1", 2048);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                outputStreamWriter.write(testStr);
                outputStreamWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
