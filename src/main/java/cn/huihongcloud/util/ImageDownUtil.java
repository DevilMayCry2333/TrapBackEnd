package cn.huihongcloud.util;

import java.io.IOException;

public class ImageDownUtil {
    public void moveFile(String oldFileName,String newFileName) {
        try {
            String cmd = "sudo cp " + oldFileName + " " + newFileName+".jpg";
            System.out.println(cmd);
            Process proc = Runtime.getRuntime().exec(cmd);
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void tarFile(String username){
        try {
            String cmd = "sudo tar -cvf /var/www/html/img" + username + ".tar /var/www/html/img";
            System.out.println(cmd);
            Process proc = Runtime.getRuntime().exec(cmd);
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(String username){
        try {
            String cmd = "sudo rm -rf /var/www/html/img/*.*";
            System.out.println(cmd);
            Process proc = Runtime.getRuntime().exec(cmd);
            proc.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
