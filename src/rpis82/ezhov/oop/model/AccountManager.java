package rpis82.ezhov.oop.model;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class AccountManager implements Iterable<Account> {

    private Account[] accounts;
    private int size = 0; // хранит кол-во не null элементов в массиве accounts

    public AccountManager(int arraySize) { accounts = new Account[arraySize]; }

    public AccountManager(Account[] accounts) {
        this.accounts = new Account[accounts.length];
        for (int i = 0; i < accounts.length; i++) {
            this.accounts[i] = accounts[i];
        }
        size += accounts.length;
    }

    /**
     * Добавляет счёт (rpis82.ezhov.oop.Account) в первое свободное место в массиве
     * accounts. Если места нет, то увелчивает кол-во элементов массива
     * в два раза.
     * @param account экземпляр счёта
     * @return булево значение
     */
    public boolean add(Account account) throws DublicateAccountNumberException {
        if (Objects.isNull(account)) throw new NullPointerException();
        for (int i = 0; i < size; i++) {
            if (accounts[i].getNumber() == account.getNumber()) throw new DublicateAccountNumberException();
        }

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                accounts[i] = account;
                ++size;
                break;
            }

            if (accounts[i] != null && (i + 1) == accounts.length) {
                Account[] longerAccountsArray = new Account[accounts.length * 2];
                for (int j = 0; j < accounts.length; j++) {
                    longerAccountsArray[j] = accounts[j];
                }
                this.accounts = longerAccountsArray;
            }
        }
        return true;
    }

    /**
     * Добавляет экземпляр счёта по определённому индексу в массив.
     * @param index индекс элемента
     * @param account экземпляр счёта
     * @return булево значение
     */
    public boolean add(int index, Account account) throws DublicateAccountNumberException {
        if (Objects.isNull(account)) throw new NullPointerException();
        if (!isCorrectIndex(index)) throw new IndexOutOfBoundsException();
        for (int i = 0; i < size; i++) {
            if (accounts[i].getNumber() == account.getNumber()) throw new DublicateAccountNumberException();
        }

        accounts[index] = account;
        ++size;
        return true;
    }

    /**
     * Метод проверки ввода корректного индекса.
     * @param index Индекс элемента.
     * @return Булево значение.
     */
    private boolean isCorrectIndex(int index) {
        return index >= 0 && index < size;
    }

    public Account get(int index) {
        if (!isCorrectIndex(index)) throw new IndexOutOfBoundsException();
        return accounts[index];
    }

    /**
     * Устанавливает новый экземпляр в элемент массива.
     * Возвращает старый экземпляр класса rpis82.ezhov.oop.Account.
     * @param index индекс элемента
     * @param account новый экземпляр класса
     * @return старый экземпляр класса
     */
    public Account set(int index, Account account) throws DublicateAccountNumberException {
        if (Objects.isNull(account)) throw new NullPointerException();
        if (!isCorrectIndex(index)) throw new IndexOutOfBoundsException();
        for (int i = 0; i < size; i++) {
            if (accounts[i].getNumber() == account.getNumber()) throw new DublicateAccountNumberException();
        }

        Account currentAccount = accounts[index];
        accounts[index] = account;
        return currentAccount;
    }

    /**
     * Удаляет экземпляр счёта. Стоящие справа элементы в массиве
     * "смещаются" влево. Последний элемент получает значение null.
     * @param index индекс элемента
     * @return удалённый экземпляр класса
     */
    public Account remove(int index) {
        if (!isCorrectIndex(index)) throw new IndexOutOfBoundsException();

        Account currentAccount = accounts[index];
        accounts[index] = null;
        for (int i = index + 1; i < accounts.length; i++) {
            accounts[i - 1] = accounts[i];
        }
        accounts[accounts.length - 1] = null;
        --size;
        return currentAccount;
    }

    public int size() { return size; }

    /**
     * Удаляет экземпляр счёта из массива, если он будет
     * эквивалентен переданному в качестве параметра счёту.
     * @param account Экземпляр Account для сравнения.
     * @return Логическое значение успеха выполнения метода.
     */
    public boolean remove(Account account) {
        if (Objects.isNull(account)) throw new NullPointerException();

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].equals(account)) {
                accounts[i] = null;
                for (int j = i + 1; j < accounts.length; j++) {
                    accounts[j - 1] = accounts[j];
                }
                accounts[accounts.length - 1] = null;
                --size;
                return true;
            }
        }
        return false;
    }

    /**
     * Возвращает индекс первого вхождения в массив объекта Account,
     * идентичного переданному в качестве параметра объекту.
     * @param account ссылка на объект для сравнения.
     * @return Индекс первого вхождения или -1, если такой объект не найден.
     */
    public int indexOf(Account account) {
        if (Objects.isNull(account)) throw new NullPointerException();

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].equals(account)) {
                return i;
            }
        }
        return -1;
    }

    public Account[] getAccounts() { return accounts; }

    /**
     * Метод, возвращающий массив счетов, содержащих в своих тарифах указанную услугу.
     * @param serviceType тип услуги
     * @return массив счетов
     */
    public Account[] getAccounts(ServiceTypes serviceType) {
        if (Objects.isNull(serviceType)) throw new NullPointerException();

        int specifiedSize = 0;
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].getTariff().getServices(serviceType)[0] != null) {
               specifiedSize++;
            }
        }
        Account[] arrayToReturn = new Account[specifiedSize];

        for (int i = 0, j = 0; i < arrayToReturn.length; j++) {
            if (accounts[j].getTariff().getServices(serviceType)[0] != null) {
                arrayToReturn[i] = accounts[j];
                i++;
            }
        }
        return arrayToReturn;
    }

    /**
     * Метод, возвращающий ссылку на счёт по его номеру.
     * @param accountNumber Номер счёта.
     * @return Ссылку на найденный счёт, в противном случае - null.
     */
    public Account getAccount(long accountNumber) {
        if (!AbstractAccount.isCorrectNumber(accountNumber))
            throw new IllegalAccountNumber("Номер счёта вышел из допустимого диапазона.");

        for (Account element : this) {
            if (element.getNumber() == accountNumber) {
                return element;
            }
        }
        throw new NoSuchElementException();
    }

    /**
     * Метод возвращает массив счетов, состоящих из счетов типа IndividualAccount
     * @return массив счетов
     */
    public Account[] getIndividualAccounts() {
        int specifiedSize = 0;
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] instanceof rpis82.ezhov.oop.model.IndividualAccount) {
                specifiedSize++;
            }
        }
        Account[] arrayToReturn = new Account[specifiedSize];

        for (int i = 0, j = 0; i < arrayToReturn.length; j++) {
            if (accounts[j] instanceof rpis82.ezhov.oop.model.IndividualAccount) {
                arrayToReturn[i] = accounts[j];
                i++;
            }
        }
        return arrayToReturn;
    }

    /**
     * Метод возвращает массив счетов, состоящих из счетов типа EntityAccount
     * @return массив счетов
     */
    public Account[] getEntityAccounts() {
        int specifiedSize = 0;
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] instanceof rpis82.ezhov.oop.model.EntityAccount) {
                specifiedSize++;
            }
        }
        Account[] arrayToReturn = new Account[specifiedSize];

        for (int i = 0, j = 0; i < arrayToReturn.length; j++) {
            if (accounts[j] instanceof rpis82.ezhov.oop.model.EntityAccount) {
                arrayToReturn[i] = accounts[j];
                i++;
            }
        }
        return arrayToReturn;
    }

    /**
     * Возвращает экземпляр тарифа по номеру счёта.
     * @param accountNumber номер счёта
     * @return экземпляр класса тарифа
     */
    public Tariff getTariff(long accountNumber) {
        if (!AbstractAccount.isCorrectNumber(accountNumber))
            throw new IllegalAccountNumber("Номер счёта вышел из допустимого диапазона.");

        for (Account element : this) {
            if (element.getNumber() == accountNumber) {
                return element.getTariff();
            }
        }
        throw new NoSuchElementException();
    }

    /**
     * Изменяет ссылку на экземпляр тарифа для счёта с заданным номером.
     * Возвращает прежнюю ссылку.
     * @param accountNumber номер счёта
     * @param tariff ссылка на экземпляр тарифа
     * @return прежний тариф счёта
     */
    public Tariff setTariff(long accountNumber, Tariff tariff) {
        if (Objects.isNull(tariff)) throw new NullPointerException();
        if (!AbstractAccount.isCorrectNumber(accountNumber))
            throw new IllegalAccountNumber("Номер счёта вышел из допустимого диапазона.");

        for (Account element : this) {
            if (element.getNumber() == accountNumber) {
                Tariff currentTariff = element.getTariff();
                element.setTariff(tariff);
                return currentTariff;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("");
        for (Account element : accounts) {
            if (element != null) {
                stringBuilder.append(String.format("%n%s%n", element.toString()));
            }
        }
        return stringBuilder.toString();
    }

    public Iterator<Account> iterator() {
        return new AccountIterator();
    }

    private class AccountIterator implements Iterator<Account> {
        int index;

        public boolean hasNext() {
            if (index < size) {
                return true;
            }
            return false;
        }

        public Account next() {
            if (!hasNext()) throw new NoSuchElementException();
            return get(index++);
        }
    }
}
