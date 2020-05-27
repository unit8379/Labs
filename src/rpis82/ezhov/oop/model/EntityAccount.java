package rpis82.ezhov.oop.model;

import java.time.LocalDate;
import java.util.Objects;

public class EntityAccount extends AbstractAccount {

    private String name;

    public EntityAccount(long number, String name) {
        super(number, new EntityTariff(new Service[]{new Service()}), LocalDate.now()); // экземпляр тарифа с одной стандартной услугой
        if (Objects.isNull(name)) throw new NullPointerException();
        this.name = name;
    }

    public EntityAccount(long number, String name, Tariff tariff, LocalDate registrationDate) {
        super(number, tariff, registrationDate);
        if (Objects.isNull(name)) throw new NullPointerException();
        this.name = name;
    }

    public String getName() { return name; }

    public void setName(String name) {
        if (Objects.isNull(name)) throw new NullPointerException();
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Entity account:\nEntity: " + name + "\n");
        return stringBuilder.append(super.toString()).toString();
    }

    @Override
    public int hashCode() {
        int code = 53 * super.hashCode() * name.hashCode();
        return code < 0 ? -code : code;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        EntityAccount entityAccount = (EntityAccount) obj;
        return (this.name == entityAccount.name) && super.equals(obj);
    }
}
