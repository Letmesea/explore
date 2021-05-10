package com.letmesea.doit.utils;

import java.io.*;

public class FileUtil {
    public static void writeFile(String path,String data){
        try {

            File file = new File(path);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            /*字符*/
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.flush();
            bw.close();

            /*字节*/
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
//            bufferedOutputStream.write(data.getBytes());
//            bufferedOutputStream.flush();
//            bufferedOutputStream.close();


            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String readFile(String url){
        try {
            File file = new File(url);
            /*字节流读入，一次一个*/
/*            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            int bytet;
            while ((bytet=bufferedInputStream.read())!=-1){
                System.out.print(bytet);
            }
            bufferedInputStream.close();*/

            /*字符流，按行读取*/
            FileInputStream fileInputStream1 = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream1);
            InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder str=new StringBuilder();
            String strt ;
            while ((strt=bufferedReader.readLine())!=null){
                str.append(strt).append("\n");
            }
            bufferedReader.close();
            return str.toString();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args){
        String url = null;
        url = FileUtil.class.getResource("/").getPath()+"/12.html";
        System.out.println(readFile(url));
    }
}
