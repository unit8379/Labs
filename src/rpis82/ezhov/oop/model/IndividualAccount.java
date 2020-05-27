package rpis82.ezhov.oop.model;

import java.time.LocalDate;
import java.util.Objects;

public class IndividualAccount extends AbstractAccount {

    private Person person;

    public IndividualAccount(long number, Person person) {
        super(number, new IndividualsTariff(new Service[]{new Service()}), LocalDate.now());
        if (Objects.isNull(person)) throw new NullPointerException();
        this.person = person;
        // new IndividualsTariff(new Service[]{new Service()}); // экземпляр тарифа с одной стандартной услугой
    }

    public IndividualAccount(long number, Person person, Tariff tariff, LocalDate registrationDate) {
        super(number, tariff, registrationDate);
        if (Objects.isNull(person)) throw new NullPointerException();
        this.person = person;
    }

    public Person getPerson() { return person; }

    public void setPerson(Person person) {
        if (Objects.isNull(person)) throw new NullPointerException();
        this.person = person;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Individual account:\nHolder: " + person.toString() + "\n");
        return stringBuilder.append(super.toString()).toString();
    }

    @Override
    public int hashCode() {
        int code = 97 * super.hashCode() * person.hashCode();
        return code < 0 ? -code : code;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        IndividualAccount individualAccount = (IndividualAccount) obj;
        return this.person.equals(individualAccount.person) && super.equals(obj);
    }
}
