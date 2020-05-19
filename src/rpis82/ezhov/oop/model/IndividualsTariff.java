package rpis82.ezhov.oop.model;

public class IndividualsTariff implements Tariff {
    private Service[] services;
    private int size = 0; // кол-во не null элементов в массиве services

    public IndividualsTariff() {
        services = new Service[8];
    }

    public IndividualsTariff(int arraySize) {
        services = new Service[arraySize];
    }

    public IndividualsTariff(Service[] services) {
        this.services = new Service[services.length];
        for (int i = 0; i < services.length; i++) {
            this.services[i] = services[i];
        }
        size += services.length;
    }

    /**
     * Добавляет ссылку на экземпляр класса в массив,
     * если свободно место в нём, иначе массив увеличивается в два раза,
     * а затем экземпляр класса записывается в массив.
     * @param service ссылка на экземпляр класса rpis82.ezhov.oop.Service
     * @return true, после завершения операции
     */
    public boolean add(Service service) {
        for (int i = 0; i < services.length; i++) {
            if (services[i] == null) {
                services[i] = service;
                ++size;
                break;
            }

            increaseServicesArray(i);
        }
        return true;
    }

    private void increaseServicesArray(int index) {
        if (services[index] != null && (index + 1) == services.length) {
            Service[] longerServicesArray = new Service[services.length * 2];
            for (int j = 0; j < services.length; j++) {
                longerServicesArray[j] = services[j];
            }
            this.services = longerServicesArray;
        }
    }

    /**
     * Добавляет ссылку на экземпляр класса в конкретный слот
     * в массиве
     * @param index индекс элемента для записи
     * @param service экземпляр класса
     * @return true, после завершения операции
     */
    public boolean add(Service service, int index) {
        if (services[index] == null) {
            services[index] = service;
            ++size;
        }
        else { // если услга по индексу массива уже присутствовала, то
            // просто обновляем услгу в этом месте без увеличения поля size
            services[index] = service;
        }
        return true;
    }

    /**
     * Возвращает ссылку на экземпляр класса по его номеру в массиве
     * @param index номер элемента
     * @return ссылка на экземпляр класса rpis82.ezhov.oop.Service
     */
    public Service get(int index) {
        return services[index];
    }

    /**
     * Возвращает ссылку на экземпляр класса по его атрибуту имени
     * @param serviceName искомый атрибут name
     * @return ссылка на экземпляр класса rpis82.ezhov.oop.Service
     */
    public Service get(String serviceName) {
        for (int i = 0; i < services.length; i++) {
            if (compareNames(i, serviceName)) {
                return services[i];
            }
        }
        return null;
    }

    /**
     * Определят есть ли в тарифе услуга с заданным именем
     * @param serviceName имя услуги
     * @return булево значение
     */
    public boolean hasService(String serviceName) {
        for (int i = 0; i < services.length; i++) {
            return compareNames(i, serviceName);
        }
        return false;
    }

    private boolean compareNames(int index, String serviceName) {
        return services[index].getName().equals(serviceName);
    }

    /**
     * Заменяет ссылку в массиве на новую. Старую возвращает.
     * @param service ссылка на новую ссылку
     * @param index номер элемента
     * @return предыдущая ссылка на экземпляр класса rpis82.ezhov.oop.Service
     */
    public Service set(Service service, int index) {
        Service currentService = services[index];
        services[index] = service;
        return currentService;
    }

    /**
     * Удаляет экземпляр rpis82.ezhov.oop.Service из массива, возвращает экземпляр,
     * который там лежал. Элементы после удалённого "смещаются" влево,
     * а последний элемент принимает значение null.
     * @param index индекс элемента
     * @return экземпляр класса, лежащий в этом элементе
     */
    public Service remove(int index) {
        Service currentService = services[index];
        /*
        services[index] = null;
        for (int i = index + 1; i < services.length; i++) {
            services[i - 1] = services[i];
        } */
        System.arraycopy(services, index + 1, services, index, services.length - index - 1);
        services[services.length - 1] = null;
        --size;
        return currentService;
    }

    /**
     * Удаляет экземпляр rpis82.ezhov.oop.Service из массива по заданному
     * имени, возвращает экземпляр, который там лежал.
     * Элементы после удалённого "смещаются" влево,
     * а последний элемент принимает значение null.
     * @param serviceName значение Name в экземпляре
     * @return экземпляр класса, лежащий в этом элементе
     */
    public Service remove(String serviceName) {
        for (int i = 0; i < services.length; i++) {
            if (compareNames(i, serviceName)) {
                Service currentService = services[i];
                services[i] = null;
                for (int j = i + 1; i < services.length; i++) {
                    services[j - 1] = services[j];
                }
                services[services.length - 1] = null;
                --size;
                return currentService;
            }
        }
        return null;
    }

    public int size() { return size; }

    /**
     * Возвращает массив услуг. Возвращаемый массив не имеет null элементов.
     * @return массив услуг (экземпляров класса rpis82.ezhov.oop.Service)
     */
    public Service[] getServicesWithoutNulls() {
        Service[] arrayToReturnWithoutNulls = new Service[size];
        for (int i = 0; i < arrayToReturnWithoutNulls.length; i++) {
            arrayToReturnWithoutNulls[i] = services[i];
        }
        return arrayToReturnWithoutNulls;
    }

    public Service[] getServices() {
        return this.services;
    }

    /**
     * Возвращает массив услуг указанного типа.
     * @param type тип услуги
     * @return массив из услуг
     */
    public Service[] getServices(ServiceTypes type) {
        int specifiedSize = 0;
        for (int i = 0; i < services.length; i++) {
            if (services[i].getType() == type) {
                specifiedSize++;
            }
        }
        Service[] arrayToReturn = new Service[specifiedSize];

        for (int i = 0, j = 0; i < arrayToReturn.length; j++) {
            if (services[j].getType() == type) {
                arrayToReturn[i] = services[j];
                i++;
            }
        }
        return arrayToReturn;
    }

    /**
     * Массив сортируется по возрастанию стоимости услуг (пузырьком)
     * и возвращается.
     * @return массив услуг
     */
    public Service[] sortedServicesByCost() {
        Service[] arrayToReturnWithoutNulls = new Service[size];
        for (int i = 0; i < arrayToReturnWithoutNulls.length; i++) {
            arrayToReturnWithoutNulls[i] = services[i];
        }

        boolean isSorted = false;
        Service buffer;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < arrayToReturnWithoutNulls.length - 1; i++) {
                if (arrayToReturnWithoutNulls[i].getCost() > arrayToReturnWithoutNulls[i + 1].getCost()) {
                    isSorted = false;
                    buffer = arrayToReturnWithoutNulls[i];
                    arrayToReturnWithoutNulls[i] = arrayToReturnWithoutNulls[i + 1];
                    arrayToReturnWithoutNulls[i + 1] = buffer;
                }
            }
        }
        return arrayToReturnWithoutNulls;
    }

    /**
     * Возвращает общую стоимость тарифа.
     * 50 - сбор за обслуживание.
     * @return стоимость тарифа
     */
    public double cost() {
        double sumServicesCost = 0;
        for (Service element : services) {
            sumServicesCost += element.getCost();
        }
        return sumServicesCost + 50;
    }
}