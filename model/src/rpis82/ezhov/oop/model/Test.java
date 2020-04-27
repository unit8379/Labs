package rpis82.ezhov.oop.model;

public class Test {

    /**
     * Тесты к первой лаб. раб.
     */
    public static void lab1tests() {
        Service service1 = new Service(); // значения по умолчанию
        Service service2 = new Service("интернет 50мб", 150);
        System.out.println(service1.getCost() + service1.getName());
        System.out.println(service2.getName() + service2.getCost());
        service2.setCost(400);
        service2.setName("интернет 200мб");
        System.out.println(service2.getName() + service2.getCost());

        Service[] services = {service1, service2};
        IndividualsTariff tariff1 = new IndividualsTariff(services);
        System.out.println(tariff1.size());
        Service service3 = new Service();
        tariff1.add(service3);
        System.out.println(tariff1.size());
        tariff1.sortedServicesByCost();
        tariff1.remove(0);
        System.out.println(tariff1.size());
        services = tariff1.sortedServicesByCost();

    }

    /**
     * Тесты ко второй лаб. раб.
     */
    public static void lab2tests() {
        Service service1 = new Service(); // значения по умолчанию
        Service service2 = new Service("интернет 50мб", 150);
        System.out.println(service1.getName() + " " + service1.getCost());
        System.out.println(service2.getName() + " " + service2.getCost());
        service2.setCost(400);
        service2.setName("интернет 200мб");
        System.out.println(service2.getName() + " " + service2.getCost());

        Service[] services = {service1, service2};

        System.out.println("Тестим IndividualsTariff через ссылку интерфейсного типа");
        IndividualsTariff tariff1 = new IndividualsTariff(services);
        Tariff tariff1_interface = tariff1;
        System.out.println(tariff1_interface.cost());

        System.out.println();
        System.out.println("Тестим ЭнтитиТариф через ссылку интерфейсного типа");
        EntityTariff tariff2 = new EntityTariff(services);
        Tariff tariff2_interface = tariff2;
        System.out.println(tariff2_interface.cost());
        System.out.println(tariff2_interface.add(service1, 2));
        System.out.println(tariff2_interface.add(service2));
        System.out.println(tariff2_interface.cost());
        System.out.println(tariff2_interface.add(service1, 0));
        System.out.println(tariff2_interface.cost());
        System.out.println(tariff2_interface.remove("интернет 200мб"));
        System.out.println(tariff2_interface.cost());
        System.out.println(tariff2_interface.get(0));
        for (Service element : tariff2_interface.sortedServicesByCost()) {
            System.out.println(element.getCost());
        }
        System.out.println(tariff2_interface.hasService("интернет 200мб"));
        System.out.println(tariff2_interface.set(service1, 1));

        System.out.println();
        System.out.println("Тестим ИндивидуалАккаунт через интерфейс Аккаунт");
        Person person1 = new Person("Gleb", "Ezhov");
        IndividualAccount individualAccount1 = new IndividualAccount(8379, person1, tariff1_interface);
        Account individualAccount1_interface = individualAccount1;
        System.out.println(individualAccount1_interface.getNumber());
        System.out.println(individualAccount1_interface.getTariff());

        System.out.println();
        System.out.println("Тестим ЕнтитиАккаунт через интерфейс Аккаунт");
        EntityAccount entityAccount1 = new EntityAccount(8379, "Gleb Ezhov");
        Account entityAccount1_interface = entityAccount1;
        System.out.println(entityAccount1_interface.getNumber());
        System.out.println(entityAccount1_interface.getTariff());
        entityAccount1_interface.setTariff(tariff2_interface);
        System.out.println(entityAccount1_interface.getTariff());

        System.out.println();
        System.out.println("Тестим АккаунтМенеджер через интерфейс Аккаунт");
        AccountManager accountManager = new AccountManager(2);
        accountManager.add(entityAccount1_interface);
        accountManager.add(1, individualAccount1_interface);
        System.out.println(accountManager.size());
        System.out.println(accountManager.getAccounts());
        accountManager.remove(0);
        System.out.println(accountManager.size());
    }

    /**
     * Метод с доп. заданием к первой лабораторной работе
     */
    public static void task1() {
        Service service1 = new Service("интернет 150 мб", 500);
        Service service2 = new Service("интернет 200 мб");
        Service service3 = new Service();

        // проинициализировал массив. первые два элемента задал сам, остальные три по умолчанию
        IndividualsTariff tariff = new IndividualsTariff(5);
        for (int i = 0; i < tariff.getServices().length; i++){

            if (i == 0) {
                tariff.add(service1);
            }
            else if (i == 1) {
                tariff.add(service2);
            }
            else {
                tariff.add(service3);
            }
        }

        // вывод
        ServicesArrayOut(tariff.getServices());
        System.out.println("размер массива " + tariff.size());

        // добавление нового экземляра услуги на место третьего элемента
        tariff.add(service1, 2);

        // вывод
        ServicesArrayOut(tariff.getServices());
        System.out.println("размер массива " + tariff.size());

        // удаление второго элемента
        tariff.remove(1);

        // вывод
        ServicesArrayOut(tariff.getServicesWithoutNulls());
        System.out.println("размер массива " + tariff.size());

        // сортировка массива по цене услуг
        tariff.sortedServicesByCost();

        // вывод
        ServicesArrayOut(tariff.getServicesWithoutNulls());
        System.out.println("размер массива " + tariff.size());

    }

    public static void task2() {
        
    }

    // метод вывода элементов массива
    private static void ServicesArrayOut(Service[] servicesArray) {
        System.out.println(servicesArray);  // адрес массива в памяти
        System.out.println();

        for (Service element : servicesArray) {
            System.out.println(element.getName() + " " + element.getCost() + "; ");
        }
    }

}