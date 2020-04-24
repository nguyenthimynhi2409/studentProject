package sample;
import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


public class Person {

    private final    StringProperty name;
    private final    IntegerProperty age;
    private final    ObjectProperty<LocalDate> birthday;

    /**
     * Default constructor.
     */
    public Person() {
        this(null, null);
    }

    public Person(String name, Integer age) {
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(2000, 9, 24));
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty NameProperty() {
        return name;
    }
    public int getAge() {return age.get(); }

    public void setAge(int age) { this.age.set(age); }

    public IntegerProperty ageProperty() {
        return age;
    }



    public LocalDate getBirthday() {
        return birthday.get();
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }


    //  @XmlJavaTypeAdapter(LocalDateAdapter.class)
//    public LocalDate getBirthday() {
//        return birthday.get();
//    }
}

