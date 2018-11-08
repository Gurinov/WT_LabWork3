package by.bsuir.gurinov.server.service;


import by.bsuir.gurinov.server.dao.InfoDao;
import by.bsuir.gurinov.server.model.Info;

import java.util.ArrayList;

public class InfoService {

    private InfoDao infoDao;

    public InfoService() {
        infoDao = new InfoDao();
    }

    /**
     * @return all info.
     */
    public ArrayList<Info> getAll() {
        return infoDao.getAll();
    }

    /**
     * Get info by name.
     */
    public Info getByName(String name) {
        return infoDao.getByName(name);
    }

    /**
     * add info.
     * @param obj info
     */
    public boolean add(Info obj) {
        if((obj != null) && (!infoDao.getAll().contains(obj))){
            infoDao.add(obj);
            return true;
        }
        return false;
    }

    /**
     * Edit info in catalog.
     * @return info
     */
    public String editInfo(Info info, String name, String mobile, String faculty, int group) {
        if(isValidValue(name) && isValidValue(faculty) && group > 0 && info != null){
            return infoDao.editInfo(info, name, mobile, faculty, group);
        }
        return "Incorrect values";
    }

    private boolean isValidValue(String str){
        if ((str.length() > 2) && (str.length() < 50)){
            return true;
        }
        return false;
    }
}
