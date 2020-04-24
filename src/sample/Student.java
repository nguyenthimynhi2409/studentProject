package sample;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Student extends Person{
    private final StringProperty nickName;
    private final StringProperty nameClass;
    private final StringProperty id;
    public Student(){
        super(null,0);
        this.id=new SimpleStringProperty(null);
        this.nameClass = new SimpleStringProperty("");
        this.nickName = new SimpleStringProperty("");
        //       this(null,null,null);
    }
    public Student(String name, Integer age,String id) {

        super(name,age);
        this.nameClass = new SimpleStringProperty("CNTT");
        this.nickName = new SimpleStringProperty("Suri");
        this.id= new SimpleStringProperty(id);
    }
// loi idField va chua nhap noColumn

    public String getNickName() {
        return nickName.get();
    }

    public void setNickName(String nickName) {
        this.nickName.set(nickName);
    }

    public StringProperty nickNameProperty() {
        return nickName;
    }

    public String getNameClass() { return nameClass.get(); }

    public void setNameClass(String nameClass) { this.nameClass.set(nameClass); }

    public StringProperty nameClassProperty() { return nameClass; }

    public String getId() { return id.get(); }

    public void setID(String id) { this.id.set(id); }

    public StringProperty idProperty() { return id; }

}
