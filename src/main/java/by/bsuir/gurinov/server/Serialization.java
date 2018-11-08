package by.bsuir.gurinov.server;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Serialization<T> {
    /**
     * serialize object to XML
     * @param obj object
     * @param fileName file path
     */
    public void serialize(ArrayList<T> obj, String fileName) {

        XMLEncoder encoder=null;
        try{
            encoder=new XMLEncoder(new FileOutputStream(fileName));
        }catch(FileNotFoundException fileNotFound){
            System.out.println("ERROR: While Creating or Opening the File");
        }
        encoder.writeObject(obj);
        encoder.close();

    }

    /**
     * deserialize object from XML
     * @param type return type
     * @param fileName file path
     */
    public ArrayList<T> deSerialize(Type type, String fileName) {

        XMLDecoder decoder=null;
        try {
            decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)));
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found");
        }
        return (ArrayList<T>)decoder.readObject();

    }

}
