package rpis82.ezhov.oop.model;

public interface Account {
    long getNumber();

    Tariff getTariff();

    void setTariff(Tariff tariff);
}
