public class Service {
    private String name;
    private double cost;

    public Service() {
        this.name = "интернет 100мб/сек";
        this.cost = 300;
    }

    // эмуляция цены по умолчанию
    public Service(String name) {
        this(name, 300);
    }

    // эмуляция имени по умолчанию
    public Service(double cost) {
        this("интернет 100мб/сек", cost);
    }

    public Service(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
