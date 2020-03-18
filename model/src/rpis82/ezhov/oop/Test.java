package rpis82.ezhov.oop;

public class Test {

    public static void lab1tests(){
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
}
