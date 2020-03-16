public class IndividualsTariff {
    private Service[] services;
    private int size;

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
    }

    /**
     * Добавляет ссылку на экземпляр класса в массив,
     * если свободно место в нём
     * @param service ссылка на экземпляр класса Service
     * @return true, после завершения операции
     */
    public boolean add(Service service) {
        for (Service element : services) {
            if (element == null) {
                element = service;
                break;
            }
        }
        return true;
    }

    /**
     * Добавляет ссылку на экземпляр класса в конкретный слот
     * в массиве
     * @param index индекс элемента для записи
     * @param service экземпляр класса
     * @return true, после завершения операции
     */
    public boolean add(Service service, int index) {
        services[index] = service;
        return true;
    }

    /**
     * Возвращает ссылку на экземпляр класса по его номеру в массиве
     * @param index номер элемента
     * @return ссылка на экземпляр класса Service
     */
    public Service get(int index) {
        return services[index];
    }

    /**
     * Возвращает ссылку на экземпляр класса по его атрибуту имени
     * @param serviceName искомый атрибут name
     * @return ссылка на экземпляр класса Service
     */
    public Service get(String serviceName) {
        for (Service element : services) {
            if (element.getName() == serviceName) {
                return element;
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
        for (Service element : services) {
            if (element.getName() == serviceName) {
                return true;
            }
        }
        return false;
    }

    /**
     * Заменяет ссылку в массиве на новую. Старую возвращает.
     * @param service ссылка на новую ссылку
     * @param index номер элемента
     * @return предыдущая ссылка на экземпляр класса Service
     */
    public Service set(Service service, int index) {
        Service currentService = services[index];
        services[index] = service;
        return currentService;
    }

    public Service remove(int index) {
        Service currentService = services[index];
        services[index] = null;
        return currentService;
    }

    public Service remove(String serviceName) {
        for (Service element : services) {
            if (element.getName() == serviceName) {
                Service currentService = element;
                element = null;
                return currentService;
            }
        }
        return null;
    }

    public int size(){
        return size;
    }

    // null в массиве быть не должно. сайз == services.length
    public Service[] getServices() {
        return services;
    }

    /**
     * Массив сортируется по возрастанию стоимости услуг (пузырьком)
     * и возвращается.
     * @return массив услуг
     */
    public Service[] sortedServicesByCost() {
        boolean isSorted = false;
        double buffer;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < services.length - 1; i++) {
                if (services[i].getCost() > services[i + 1].getCost()) {
                    isSorted = false;
                    buffer = services[i].getCost();
                    services[i].setCost(services[i + 1].getCost());
                    services[i + 1].setCost(buffer);
                }
            }
        }
        return services;
    }

    /**
     * Возвращает общую стоимость тарифа.
     * 50 - сбор за обслуживание.
     * @return стоимость тарифа
     */
    public double cost() {
        double summServicesCost = 0;
        for (Service element : services) {
            summServicesCost += element.getCost();
        }
        return summServicesCost + 50;
    }
}


































