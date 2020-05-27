package rpis82.ezhov.oop.model;

import java.util.Objects;

public class Person {
    private String fName;
    private String sName;

    public Person(String fName, String sName) {
        if (Objects.isNull(fName) || Objects.isNull(sName)) throw new NullPointerException();

        this.fName = fName;
        this.sName = sName;
    }

    public String getFName() { return fName; }

    public String getSName() { return sName; }

    @Override
    public String toString() {
        return String.format("%1$s %2$s", fName, sName);
    }

    @Override
    public int hashCode() {
        int code = fName.hashCode() * sName.hashCode();
        return code < 0 ? -code : code;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Person person = (Person) obj;
        return (this.fName.compareTo(person.fName) == 0) && (this.sName.compareTo(person.sName) == 0);
    }
}
