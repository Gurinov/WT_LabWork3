package by.bsuir.gurinov.server.dao;

import by.bsuir.gurinov.server.Serialization;
import by.bsuir.gurinov.server.model.Info;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class InfoDao {
    private ArrayList<Info> info = new ArrayList<Info>();
    private Serialization<Info> serialization = new Serialization<>();

    public InfoDao() {
        info = serialization.deSerialize( new TypeToken<ArrayList<Info>>() {}.getType(),"src/main/java/by/bsuir/gurinov/server/files/info.xml");
        //info.add(new Info("qwerty", "12345", 173, "asd"));
    }

    public ArrayList<Info> getAll() {
        return info;
    }

    /**
     * Get student by name.
     */
    public Info getByName(String name) {
        for (Info info: info) {
            if (info.getName().equals(name)){
                return info;
            }
        }
        return null;
    }

    /**
     * add info.
     * @param obj info
     */
    public void add(Info obj) {
        info.add(obj);
        serialization.serialize(info, "src/main/java/by/bsuir/gurinov/server/files/info.xml");
    }

    /**
     * Edit info in catalog.
     */
    public String editInfo(Info info1, String name, String mobile, String faculty, int group) {
        if (info.contains(info1)) {
            info.get(info.indexOf(info1)).setName(name);
            info.get(info.indexOf(info1)).setMobile(mobile);
            info.get(info.indexOf(info1)).setFaculty(faculty);
            info.get(info.indexOf(info1)).setGroup(group);
            serialization.serialize(info, "src/main/java/by/bsuir/gurinov/server/files/info.xml");
            return "Success";
        }
        return "Info not found";
    }
}
