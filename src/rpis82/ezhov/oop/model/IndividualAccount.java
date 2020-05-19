package rpis82.ezhov.oop.model;

public class IndividualAccount extends AbstractAccount {

    private Person person;

    public IndividualAccount(long number, Person person) {
        super(number, new IndividualsTariff(new Service[]{new Service()}));
        this.person = person;
        // new IndividualsTariff(new Service[]{new Service()}); // экземпляр тарифа с одной стандартной услугой
    }

    public IndividualAccount(long number, Person person, Tariff tariff) {
        super(number, tariff);
        this.person = person;
    }

    public Person getPerson() { return person; }

    public void setPerson(Person person) { this.person = person; }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Individual account:\nHolder: " + person.toString() + "\n");
        return stringBuilder.append(super.toString()).toString();
    }

    @Override
    public int hashCode() {
        return 97 * super.hashCode() * person.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        IndividualAccount individualAccount = (IndividualAccount) obj;
        return this.person.equals(individualAccount.person) && super.equals(obj);
    }
}
