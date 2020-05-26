package rpis82.ezhov.oop.model;

import java.util.Arrays;

public abstract class AbstractAccount implements Account {
    private long number;
    private Tariff tariff;

    protected AbstractAccount(long number, Tariff tariff) {
        this.number = number;
        this.tariff = tariff;
    }

    public long getNumber() { return number; }

    public Tariff getTariff() { return tariff; }

    public void setTariff(Tariff tariff) { this.tariff = tariff; }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Number: " + number + "\nServices:");
        for (Service element : tariff.getServices()) {
            stringBuilder.append(String.format("%n%s", element.toString()));
        }
        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        int code = (int)number * tariff.getServices().length;
        return code < 0 ? -code : code;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        AbstractAccount abstractAccount = (AbstractAccount) obj;
        return (this.number == abstractAccount.number) && (this.tariff.getServices().length == abstractAccount.tariff.getServices().length);
    }
}
