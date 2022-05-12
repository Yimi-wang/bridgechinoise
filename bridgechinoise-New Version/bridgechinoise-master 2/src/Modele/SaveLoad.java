package Modele;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class SaveLoad {
    public void Save(Jeu j, Histoire h) {
        System.out.println("Donner le nom pour save");
        Scanner input = new Scanner(System.in);
        String Save = input.nextLine();
        String SaveH = Save + "H";
        try {
            FileOutputStream fileOut = new FileOutputStream(Save);

            //创建一个ObjectOutputStream
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);

            //将对象写入输出流
            objOut.writeObject(j);
            //同上，保存历史记录
            fileOut = new FileOutputStream(SaveH);
            objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(h);

            objOut.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


    public void Load() {
        try {
            System.out.println("Donner le nom pour load");
            Scanner input = new Scanner(System.in);
            String Save = input.nextLine();
            String SaveH = Save + "H";
            //读取对象
            FileInputStream fileIn = new FileInputStream(Save);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            //读取历史记录
            FileInputStream fileInH = new FileInputStream(SaveH);
            ObjectInputStream objInH = new ObjectInputStream(fileIn);

            Jeu j = (Jeu) objIn.readObject();
            Histoire h = (Histoire) objInH.readObject();
            objIn.close();
            objInH.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


}

