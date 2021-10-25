package storage;

import model.TelephoneNumber;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOPhoneNumber {
    private static IOPhoneNumber ioPhoneNumber;
    private IOPhoneNumber(){
    }
    public static IOPhoneNumber getInstance(){
        if(ioPhoneNumber==null){
            ioPhoneNumber=new IOPhoneNumber();
        }
        return ioPhoneNumber;
    }
    public List<TelephoneNumber> readFile(String path) throws IOException, ClassNotFoundException {
        File file = new File(path);
        if (!file.exists()){
            file.createNewFile();
        }
        if (file.length() >0){
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();
            List<TelephoneNumber> list = (List<TelephoneNumber>) object;
            objectInputStream.close();
            fileInputStream.close();
            return list;
        }
        else return new ArrayList<>();
    }
    public void writeFile(List<TelephoneNumber> telephoneNumberList,String path) throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(telephoneNumberList);
        objectOutputStream.close();
        fileOutputStream.close();
    }
}
