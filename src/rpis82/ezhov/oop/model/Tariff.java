package rpis82.ezhov.oop.model;

public interface Tariff {
    boolean add(Service service);

    boolean add(Service service, int index);

    Service get(int index);

    Service get(String serviceName);

    boolean hasService(String serviceName);

    Service set(Service service, int index);

    Service remove(int index);

    Service remove(String serviceName);

    int size();

    Service[] getServices();

    Service[] getServices(ServiceTypes type);

    Service[] sortedServicesByCost();

    double cost();
}
