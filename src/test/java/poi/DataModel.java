package poi;

/**
 * Created by Diana on 29.12.2016.
 */
public class DataModel {
    private String name;
    private String surname;

    public DataModel(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}

