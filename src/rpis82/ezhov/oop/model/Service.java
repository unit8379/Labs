package rpis82.ezhov.oop.model;

public final class Service implements Cloneable {
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

    @Override
    public String toString() {
        // имя услуги (40 символов макс.) + цена (2 цифры после запятой)
        return String.format("%1$.40s \\ %2$.2f р.", name, cost);
    }

    @Override
    public int hashCode() {
        return name.hashCode() * ((Double)cost).hashCode() * type.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Service service = (Service) obj;
        return (this.name.compareTo(service.name) == 0) && (Double.compare(this.cost, service.cost) == 0) && this.type == service.type;
    }

    @Override
    public Service clone() throws CloneNotSupportedException {
        // здесь только поверхностное клонирование, т.к. поля не хранят ссылки на другие объекты
        return (Service)super.clone();
    }

    /*public void setName(String name) {
        this.name = name;
    }*/

    /*public void setCost(double cost) {
        this.cost = cost;
    }*/
}
