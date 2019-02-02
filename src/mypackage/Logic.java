/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypackage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;

/**
 *
 * @author Hallur
 */
public class Logic {

    private final HashMap<String, Long> hm = new HashMap<>();

    public void fileChecker(String path) throws FileNotFoundException, IOException {
        RandomAccessFile raf = new RandomAccessFile(path, "rw");
        while (raf.getFilePointer() < raf.length()) {
            Long offset = raf.getFilePointer();
            String key = raf.readLine().split(",")[0];
            hm.put(key, offset);
        }
        System.out.println("database file checked! hashmap values are: " + hm);
    }

    public void set(String key, String name, int age, String path) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(path, "rw");
        if (hm.containsKey(key)) {
            System.out.println("this key has already been used... try another");
            return;
        }
        String data = key + ",{\"name\":\"" + name + "\",\"age\":\"" + age + "\"}\n";
        raf.seek(raf.length());
        Long offset = raf.getFilePointer();
        raf.writeBytes(data);
        raf.seek(offset);
        hm.put(key, offset);
        System.out.println(raf.readLine());
    }

    public void getRow(String key, String path) throws FileNotFoundException, IOException {
        RandomAccessFile raf = new RandomAccessFile(path, "rw");
        Long offset = hm.get(key);
        raf.seek(offset);
        System.out.println("found row : " + raf.readLine());
    }

    public void getAll(String path) throws FileNotFoundException, IOException {
        RandomAccessFile raf = new RandomAccessFile(path, "rw");
        String all = "";
        while (raf.getFilePointer() < raf.length()) {
            all += raf.readLine() + "\n";
        }
        if(all.isEmpty()){
            System.out.println("database is empty! please set some data in it!");
            return;
        }
        System.out.println(all);
    }
}