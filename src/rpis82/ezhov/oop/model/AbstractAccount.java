package rpis82.ezhov.oop.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public abstract class AbstractAccount implements Account {
    private long number;
    private Tariff tariff;
    private LocalDate registrationDate;

    protected AbstractAccount(long number, Tariff tariff, LocalDate registrationDate) {
        if (Objects.isNull(tariff)) throw new NullPointerException();
        if (registrationDate.isAfter(LocalDate.now())) throw new IllegalArgumentException();
        if (!isCorrectNumber(number)) throw new IllegalAccountNumber("Номер счёта вышел из допустимого диапазона.");

        this.number = number;
        this.tariff = tariff;
        this.registrationDate = registrationDate;
    }

    public static boolean isCorrectNumber(long number) {
        long upperBound = 999999999999999L;
        long bottomBound = 1000000000001L;
        return number >= bottomBound && number <= upperBound;
    }

    public long getNumber() { return number; }

    public Tariff getTariff() { return tariff; }

    public void setTariff(Tariff tariff) {
        if (Objects.isNull(tariff)) throw new NullPointerException();
        this.tariff = tariff;
    }

    public LocalDate getRegistrationDate() { return this.registrationDate; }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Number: " + number
                + "\nRegistration Date: " + registrationDate + "\nServices:");
        for (Service element : tariff.getServices()) {
            stringBuilder.append(String.format("%n%s", element.toString()));
        }
        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        int code = (int)number * tariff.getServices().length * registrationDate.hashCode();
        return code < 0 ? -code : code;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        AbstractAccount abstractAccount = (AbstractAccount) obj;
        return (this.number == abstractAccount.number) && (this.tariff.getServices().length == abstractAccount.tariff.getServices().length)
                && (this.registrationDate.isEqual(abstractAccount.registrationDate));
    }
}
