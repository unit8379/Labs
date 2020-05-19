package rpis82.ezhov.oop.model;

public final class Service {
    private final String name;
    private final double cost;
    private final ServiceTypes type;

    // значения по умолчанию
    private final String NAME = "интернет 100мб/сек";
    private final double COST = 300;
    private final ServiceTypes TYPE = ServiceTypes.INTERNET;

    public Service() {
        this.name = NAME;
        this.cost = COST;
        this.type = TYPE;
    }

    public Service(String name, double cost, ServiceTypes type) {
        this.name = name;
        this.cost = cost;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public ServiceTypes getType() {
        return type;
    }

    /*public void setName(String name) {
        this.name = name;
    }*/

    /*public void setCost(double cost) {
        this.cost = cost;
    }*/
}
