package rpis82.ezhov.oop.model;

public class AccountManager {

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
    public boolean add(Account account) {
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
    public boolean add(int index, Account account) {
        accounts[index] = account;
        ++size;
        return true;
    }

    public Account get(int index) {
        return accounts[index];
    }

    /**
     * Устанавливает новый экземпляр в элемент массива.
     * Возвращает старый экземпляр класса rpis82.ezhov.oop.Account.
     * @param index индекс элемента
     * @param account новый экземпляр класса
     * @return старый экземпляр класса
     */
    public Account set(int index, Account account) {
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

    public Account[] getAccounts() { return accounts; }

    /**
     * Метод, возвращающий массив счетов, содержащих в своих тарифах указанную услугу.
     * @param serviceType тип услуги
     * @return массив счетов
     */
    public Account[] getAccounts(ServiceTypes serviceType) {
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
        for (Account element : accounts) {
            if (element.getNumber() == accountNumber) {
                return element.getTariff();
            }
        }
        return null;
    }

    /**
     * Изменяет ссылку на экземпляр тарифа для счёта с заданным номером.
     * Возвращает прежнюю ссылку на экземпляр класса rpis82.ezhov.oop.IndividualsTariff.
     * @param accountNumber номер счёта
     * @param tariff ссылка на экземпляр тарифа
     * @return прежний тариф счёта
     */
    public Tariff setTariff(long accountNumber, Tariff tariff) {
        for (Account element : accounts) {
            if (element.getNumber() == accountNumber) {
                Tariff currentTariff = element.getTariff();
                element.setTariff(tariff);
                return currentTariff;
            }
        }
        return null;
    }
}
