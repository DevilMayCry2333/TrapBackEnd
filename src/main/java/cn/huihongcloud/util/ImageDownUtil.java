package cn.huihongcloud.util;

import java.io.File;
import java.io.IOException;

public class ImageDownUtil {
    public void moveFile(String oldFileName,String newFileName) {
        try {
            String cmd = "sudo cp " + oldFileName + " " + newFileName+".jpg";

            Process proc = Runtime.getRuntime().exec(cmd);
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void tarFile(String username){
        try {
            String cmd = "sudo tar -cvf /var/www/html/img" + username + ".tar /var/www/html/img";

            Process proc = Runtime.getRuntime().exec(cmd);
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int deleteFile(File file){
        int code = 111111;
        try {
                String[] childFilePath = file.list();//获取文件夹下所有文件相对路径
                for (String path:childFilePath){
                    File childFile= new File(file.getAbsoluteFile()+"/"+path);
                    childFile.delete();
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }

    public void MkDir(){
        try {
            String cmd = "sudo mkdir /var/www/html/img";

            Process proc = Runtime.getRuntime().exec(cmd);
            proc.waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
