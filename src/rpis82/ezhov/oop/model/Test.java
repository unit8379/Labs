package rpis82.ezhov.oop.model;

import jdk.nashorn.internal.ir.LiteralNode;

import java.security.spec.RSAOtherPrimeInfo;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Iterator;

public class Test {

    /**
     * Тесты к первой лаб. раб.
     */ /*
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

    } */

    /**
     * Тесты ко второй лаб. раб.
     */ /*
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
    } */

    /**
     * Тесты к третьей лаб. раб.
     */ /*
    public static void lab3tests() {
        Service service1 = new Service("интернет 5пб/мкс", 500, ServiceTypes.ADDITIONAL_SERVICE);
        System.out.println(service1.getType());
        Service service2 = new Service();
        Service[] services = { service1, service2 };
        Tariff tariff1 = new IndividualsTariff(services);
        Tariff tariff2 = new EntityTariff(services);
        System.out.println(tariff1.getServices(ServiceTypes.INTERNET)[0].getType() + " " + tariff2.getServices(ServiceTypes.ADDITIONAL_SERVICE)[0].getType());
        Person person1 = new Person("Gleb", "Ezhov");
        IndividualAccount account1 = new IndividualAccount(1, person1, tariff1);
        System.out.println(account1.getPerson().getFName() + " " + account1.getPerson().getSName());
        EntityAccount account2 = new EntityAccount(2, "company", tariff2);
        System.out.println(account2.getNumber());

        // тест новых методов АккаунтМенеджера
        AccountManager accManager1 = new AccountManager(2);
        accManager1.add(account1);
        accManager1.add(account2);
        System.out.println(accManager1.getAccounts(ServiceTypes.ADDITIONAL_SERVICE)[0].getTariff().getServices(ServiceTypes.ADDITIONAL_SERVICE)[0].getType());
        System.out.println(accManager1.getEntityAccounts()[0].getClass());
        System.out.println(accManager1.getIndividualAccounts()[0].getClass());
    } */

     /*
    public static void lab4tests() {
        // переопределённые методы класса Object в классе Service
        Service service1 = new Service("интернет 5пб/мкс", 500, ServiceTypes.ADDITIONAL_SERVICE);
        Service service2 = new Service();
        System.out.println(service1.toString());
        System.out.println(service1.hashCode());
        System.out.println(service1.equals(service2));
        try {
            Service serviceClone = service1.clone();
            System.out.println(serviceClone.getType() + " " + serviceClone.getName() + " " + serviceClone.getCost());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        // переопределённые методы класса Object в классе Person
        Person person1 = new Person("Gleb", "Ezhov");
        System.out.println(person1.toString());
        System.out.println(person1.hashCode());
        System.out.println("");

        System.out.println("Тест индивидуал тарифов");
        // переопределённые методы класса Object в классе IndividualsTariff
        Service[] services = { service1, service2 };
        IndividualsTariff individualsTariff = new IndividualsTariff(services);
        System.out.println(individualsTariff.hashCode());
        System.out.println(individualsTariff.toString() + "\n");
        try {
            Tariff tariff1 = individualsTariff.clone();
            System.out.println(tariff1.toString());
            System.out.println(individualsTariff.equals(tariff1));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        // переопределённые методы класса Object в классе EntityTariff
        System.out.println("\nТест ентити тарифов");
        EntityTariff entityTariff = new EntityTariff(services);
        System.out.println(entityTariff.hashCode());
        System.out.println(entityTariff.toString() + "\n");
        try {
            Tariff tariff2 = entityTariff.clone();
            System.out.println(tariff2.toString());
            System.out.println(entityTariff.equals(tariff2));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        // тест аккаунт менеджера
        System.out.println("\nТест аккаунтов и аккаунт менеджера");
        IndividualAccount account1 = new IndividualAccount(1, person1, individualsTariff);
        EntityAccount account2 = new EntityAccount(2, "company", entityTariff);
        AccountManager accManager1 = new AccountManager(2);
        accManager1.add(account1);
        accManager1.add(account2);
        System.out.println(accManager1.toString());

        // тест новых методов
        System.out.println(individualsTariff.lastIndexOf(service1));
        System.out.println(entityTariff.indexOf(service2));
        System.out.println(entityTariff.remove(service2));
        System.out.println(individualsTariff.remove(service2));
        System.out.println(entityTariff.toString());
        System.out.println(individualsTariff.toString());
        System.out.println(accManager1.indexOf(account2));
        System.out.println(accManager1.remove(account2));
        System.out.println(accManager1.toString());
    } */

    public static void lab5tests() {
        Service service1 = new Service("интернет 5пб/мкс", 500, ServiceTypes.ADDITIONAL_SERVICE, LocalDate.of(2020, 05, 15));
        Service service2 = new Service();
        System.out.println(service1.toString());
        Service[] services = { service1, service2 };
        IndividualsTariff individualsTariff = new IndividualsTariff(services);
        EntityTariff entityTariff = new EntityTariff(services);
        //entityTariff.get(null);
        LocalDate oldDate = LocalDate.of(2016, Month.NOVEMBER, 1);
        LocalDate newDate = LocalDate.of(2016, Month.NOVEMBER, 9);
        System.out.println(Period.between(oldDate, newDate).getMonths());
        System.out.println("ентити кост: " + entityTariff.cost() + "; индивидуал кост: " + individualsTariff.cost());
        

    }

    public static void lab6tests() {
        Service service1 = new Service("интернет 5пб/мкс", 500, ServiceTypes.ADDITIONAL_SERVICE, LocalDate.of(2020, 05, 15));
        Service service2 = new Service();
        System.out.println(service2.compareTo(service1));
        Service[] services = { service1, service2 };
        IndividualsTariff individualsTariff = new IndividualsTariff(services);
        EntityTariff entityTariff = new EntityTariff(services);
        for (Service service : individualsTariff.sortedServicesByCost()) {
            System.out.println(service.toString());
        }

        System.out.println("");
        System.out.println("Iterator test:");
        System.out.println("entity iterator:");
        //Iterator entityIterator = entityTariff.iterator();
        for (Service element : entityTariff) {
            System.out.println(element.toString());
        }
        System.out.println("individual iterator:");
        //Iterator individualIterator = individualsTariff.iterator();
        for (Service element : individualsTariff) {
            System.out.println(element.toString());
        }
        individualsTariff.get("интернет 5пб/мкс");
        System.out.println(entityTariff.cost());
        System.out.println(individualsTariff.cost());
    }

    /**
     * Метод с доп. заданием к первой лабораторной работе
     */ /*
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

    } */

     /*
    public static void task4() {
        Service service1 = new Service("интернет 5пб/мкс", 500, ServiceTypes.ADDITIONAL_SERVICE);
        Service service2 = new Service();
        Person person1 = new Person("Gleb", "Ezhov");
        Service[] services = { service1, service2 };
        IndividualsTariff individualsTariff = new IndividualsTariff(services);
        EntityTariff entityTariff = new EntityTariff(services);
        IndividualAccount account1 = new IndividualAccount(1, person1, individualsTariff);
        EntityAccount account2 = new EntityAccount(2, "company", entityTariff);
        AccountManager accManager1 = new AccountManager(2);

        System.out.println(service1.hashCode());
        System.out.println(service2.hashCode());
        System.out.println(person1.hashCode());
        System.out.println(individualsTariff.hashCode());
        System.out.println(entityTariff.hashCode());
        System.out.println(account1.hashCode());
        System.out.println(account2.hashCode());
        System.out.println(accManager1.hashCode());
    } */

    // метод вывода элементов массива
    private static void ServicesArrayOut(Service[] servicesArray) {
        System.out.println(servicesArray);  // адрес массива в памяти
        System.out.println();

        for (Service element : servicesArray) {
            System.out.println(element.getName() + " " + element.getCost() + "; ");
        }
    }

}
