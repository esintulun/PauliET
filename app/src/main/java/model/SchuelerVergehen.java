package model;

public class SchuelerVergehen {

    private String name;
    private String vergehen;
    private long id;
    private boolean checked;

    public SchuelerVergehen(String name, String vergehen, long id, boolean checked) {
        this.name = name;
        this.vergehen = vergehen;
        this.id = id;
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVergehen() {
        return vergehen;
    }

    public void setVergehen(String vergehen) {
        this.vergehen = vergehen;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return name + " x " + vergehen;
    }
}
