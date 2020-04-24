package sample;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of persons. This is used for saving the
 * list of persons to XML.
 *
 * @author Marco Jakob
 */
@XmlRootElement(name = "persons")
public class PersonListWrapper {

    private List<Student> students;

    @XmlElement(name = "student")
    public List<Student> getStudent() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}