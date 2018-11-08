package by.bsuir.gurinov.server.controller;

import by.bsuir.gurinov.server.model.Info;
import by.bsuir.gurinov.server.service.InfoService;

import java.util.Scanner;

public class InfoController {

    private InfoService infoService;

    public InfoController() {
        infoService = new InfoService();
    }

    /**
     * View 1 info.
     * @param name name.
     * @return response.
     */
    public String findInfo(String name){
        for (Info info: infoService.getAll()) {
            if(info.getName().equals(name))
            return info.toString();
        }
        return "Error";
    }

    /**
     * Edit book.
     * @param name books name.
     * @return response.
     */
    public String editInfo(String pastName, String name, String mobile, int group, String faculty){
        Info info = infoService.getByName(pastName);
        if (info != null){
            return infoService.editInfo(info, name, mobile, faculty, group);
        }
        return "Book not found";
    }

    /**
     * Add.
     */
    public String addInfo(String name, String mobile, int group, String faculty){
        infoService.add(new Info(name, mobile, group, faculty));
        return "Success";
    }
}
