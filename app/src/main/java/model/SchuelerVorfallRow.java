package model;

import java.util.Date;
import java.util.List;

public class SchuelerVorfallRow {

    //TODO id soll int
    public String id;
    public Date datum;
    public String uhrzeit;
    public String info;

    public Kollege kollege;
    public Vergehen vergehen;

    public String kollegeName;
    public String vergehenTitel;

}
