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
}
