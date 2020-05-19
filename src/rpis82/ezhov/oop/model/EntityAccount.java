package rpis82.ezhov.oop.model;

public class EntityAccount extends AbstractAccount {

    private String name;

    public EntityAccount(long number, String name) {
        super(number, new EntityTariff(new Service[]{new Service()})); // экземпляр тарифа с одной стандартной услугой
        this.name = name;
    }

    public EntityAccount(long number, String name, Tariff tariff) {
        super(number, tariff);
        this.name = name;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
