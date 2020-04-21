package rpis82.ezhov.oop.model;

public class EntityAccount implements Account {

    private long number;
    private String name;
    private Tariff tariff;

    public EntityAccount(long number, String name) {
        this.number = number;
        this.name = name;
        tariff = new EntityTariff(new Service[]{new Service()}); // экземпляр тарифа с одной стандартной услугой
    }

    public EntityAccount(long number, String name, Tariff tariff) {
        this.number = number;
        this.name = name;
        this.tariff = tariff;
    }

    public long getNumber() { return number; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Tariff getTariff() { return tariff; }

    public void setTariff(Tariff tariff) { this.tariff = tariff; }
}
