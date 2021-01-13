import java.io.*;
import java.util.ArrayList;

public class createDir {
    public static String rootDir = "C:\\Users\\weitianhao\\Desktop\\new\\";        //用于存储的根目录
    public static void main(String[] args) throws IOException {
        /*
        System.out.print("please input dir path:");
        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
        String dirName = bufr.readLine();
        File fileDir = new File(dirName);
        createDirs(null,fileDir);
         */

        ArrayList<File> allFiles = new ArrayList<File> ();
        System.out.print("please input dir path:");
        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
        String dir = bufr.readLine();           //获取到源文件夹路径
        File fileDir = new File(dir);
        getAllFile(fileDir,allFiles);           //获取到所有文件对象，然后创建即可
        createDirs(allFiles);                   //将获取到的文件集合，调用创建

        System.out.println("Hello World!!");
    }

    public static void createDirs(ArrayList<File> allFiles){
        //allFiles表示所有文件及文件夹的集合
        for(File file:allFiles){
            if(file.isDirectory()){
                //如果是文件夹，则就创建文件夹（替换路径）
                createDir(file);
            }
            else {
                //如果是文件，则创建文件
                createFile(file);
            }
        }
    }

    public static void createFile(File file) {
        //获取传入的File对象，创建文件
        String s1 = file.getAbsolutePath();
        String s2 = "C:\\\\Users\\\\weitianhao\\\\Desktop\\\\test";
        String s3 = s1.replaceAll(s2,rootDir);
        File newFile = new File(s3);        //重新new一个对象
        //然后创建该文件
        try {
            BufferedWriter bufw = new BufferedWriter(new FileWriter(newFile));
            bufw.close();
        } catch (IOException e) {
            System.out.println("create file failed!!");
            //e.printStackTrace();
        }
    }

    public static void createDir(File file){
        //获取传入的File对象，创建文件夹
        String s1 = file.getAbsolutePath();     //获取对象的绝对路径
        String s2 = "C:\\\\Users\\\\weitianhao\\\\Desktop\\\\test";         //当前仅测试test路径下的文件，后续这里可以使用一个参数进行传递
        String s3 = s1.replaceAll(s2,rootDir);        //替换之后的路径信息
        File fileDir = new File(s3);
        Boolean result = fileDir.mkdirs();
        if(result){
            System.out.println("mkdir success");
        }
        else {
            System.out.println("mkdir failed");
        }
        System.out.println(s3);
    }

    public static void getAllFile(File fileDir, ArrayList<File> allFiles){
        File [] files = fileDir.listFiles();        //获取目录下的文件列表
        for(File file:files){
            if(file.isDirectory()) {
                allFiles.add(file);             //文件夹也添加到集合中，在输出的时候判断
                getAllFile(file, allFiles);  //如果是文件夹，就递归遍历
            }
            else
                allFiles.add(file);     //如果是文件，就添加到集合中
        }
    }

}
