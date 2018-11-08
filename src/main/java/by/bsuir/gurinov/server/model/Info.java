package by.bsuir.gurinov.server.model;

import java.io.Serializable;

public class Info implements Serializable {
    String name;
    String mobile;
    int group;
    String faculty;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "name='" + name + '\''
                + ", surname='" + group + '\''
                + ", mobile='" + group + '\''
                + ", group='" + group + '\''
                + ", faculty='" + faculty + "\'\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Info info = (Info) o;
        return this.getName().equals(info.getName())
                && this.getGroup() == info.getGroup()
                && this.getMobile().equals(info.getMobile())
                && this.getFaculty().equals(info.getFaculty());
    }

    public Info(String name, String mobile, int group, String faculty) {
        this.name = name;
        this.mobile = mobile;
        this.group = group;
        this.faculty = faculty;
    }

    public Info() {
    }
}
