package diwhy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Serializer {
    //ArrayList<Object> objs = new ArrayList<>();
    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> read(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ArrayList<T> objs = new ArrayList<T>();
        try {
            Object tempObj = objectInputStream.readObject();
            objs = (ArrayList<T>) tempObj; 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
        finally{
            objectInputStream.close();
            fileInputStream.close();
        }
        return objs;
    }
    public static void write(Serializable obj, String filename) throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        try{
            objectOutputStream.writeObject(obj);
        } catch(IOException e){
            e.printStackTrace();
        } finally {
            objectOutputStream.close();
            fileOutputStream.close();
        }
        return;
    }
}
