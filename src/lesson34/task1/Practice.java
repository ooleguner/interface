package lesson34.task1;

import java.io.*;

public class Practice {
    /*
    copy file content
     */
    public static void copyFileContent(String fileFromPath, String fileToPath) throws Exception {
        /*
        1- перевірити чи файл існує
        2 - перевірити права
        3- зчитати з fileFromPath
        4 - записати в fileToPath
         */
        validate(fileFromPath, fileToPath);
        writeToFile(fileToPath, readFromFile(fileFromPath));
    }

    public static void main(String[] args) throws Exception {
        copyFileContent("C:\\java\\test2.txt" , "C:\\java\\test3.txt");
    }


    private static StringBuffer readFromFile(String path) {
        StringBuffer res = new StringBuffer();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                res.append(line + "\n");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist");
        } catch (IOException e) {
            System.err.println("reading fail");
        }

        return res;
    }

    private static void writeToFile(String path, StringBuffer contentToWrite) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, true))) {
            bufferedWriter.append(contentToWrite);
            // bufferedWriter.flush();
        } catch (IOException e) {
            System.err.println("Can not write to file");
        }
    }

    private static void validate(String fileFromPath, String fileToPath) throws Exception {
        File fileFrom = new File(fileFromPath);
        File fileTo = new File(fileToPath);
        if (!fileFrom.exists()) {
            throw new FileNotFoundException("File " + fileFrom + " does not exist");
        }
        if (!fileTo.exists()) {
            throw new FileNotFoundException("File " + fileTo + " does not exist");
        }
        if (!fileFrom.canRead()) {
            throw new Exception("File " + fileFrom + " does not have permossion to be read");
        }
        if (!fileTo.canWrite()) {
            throw new Exception("File " + fileTo + " does not have permossion to be written");
        }


    }
}
