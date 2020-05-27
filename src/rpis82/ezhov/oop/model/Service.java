package rpis82.ezhov.oop.model;

import java.time.LocalDate;
import java.util.Objects;

public final class Service implements Cloneable {
    private final String name;
    private final double cost;
    private final ServiceTypes type;
    private final LocalDate activationDate;

    // значения по умолчанию
    private final String NAME = "интернет 100мб/сек";
    private final double COST = 300;
    private final ServiceTypes TYPE = ServiceTypes.INTERNET;
    private final LocalDate ACTIVATION_DATE = LocalDate.now();

    public Service() {
        this.name = NAME;
        this.cost = COST;
        this.type = TYPE;
        this.activationDate = ACTIVATION_DATE;
    }

    public Service(String name, double cost, ServiceTypes type, LocalDate activationDate) {
        if (Objects.isNull(name) || Objects.isNull(type)) throw new NullPointerException();
        if (cost < 0 || activationDate.isAfter(LocalDate.now())) throw new IllegalArgumentException();

        this.name = name;
        this.cost = cost;
        this.type = type;
        this.activationDate = activationDate;
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

    public LocalDate getActivationDate() { return activationDate; }

    @Override
    public String toString() {
        // имя услуги (40 символов макс.) + цена (2 цифры после запятой)
        return String.format("%1$.40s \\ %2$.2f р. Активировано: %3$s", name, cost, activationDate);
    }

    @Override
    public int hashCode() {
        int code = name.hashCode() * ((Double)cost).hashCode() * type.hashCode() * activationDate.hashCode();
        return code < 0 ? -code : code;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Service service = (Service) obj;
        return (this.name.compareTo(service.name) == 0) && (Double.compare(this.cost, service.cost) == 0)
                && this.type == service.type && this.activationDate.isEqual(service.activationDate);
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
