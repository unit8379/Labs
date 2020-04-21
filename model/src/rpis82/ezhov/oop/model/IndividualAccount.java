package rpis82.ezhov.oop.model;

public class IndividualAccount implements Account {

    private long number;
    private Person person;
    private Tariff tariff;

    public IndividualAccount(long number, Person person) {
        this.number = number;
        this.person = person;
        tariff = new IndividualsTariff(new Service[]{new Service()}); // экземпляр тарифа с одной стандартной услугой
    }

    public IndividualAccount(long number, Person person, Tariff tariff) {
        this.number = number;
        this.person = person;
        this.tariff = tariff;
    }

    public long getNumber() { return number; }

    public Person getPerson() { return person; }

    public void setPerson(Person person) { this.person = person; }

    public Tariff getTariff() { return tariff; }

    public void setTariff(Tariff tariff) { this.tariff = tariff; }
}
