package rpis82.ezhov.oop.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public interface Tariff extends Iterable<Service>, Collection<Service> {
    boolean add(Service service);

    boolean add(Service service, int index);

    Service get(int index);

    Service get(String serviceName);

    boolean hasService(String serviceName);

    Service set(Service service, int index);

    Service remove(int index);

    Service remove(String serviceName);

    //boolean remove(Service service);

    int indexOf(Service service);

    int lastIndexOf(Service service);

    int size();

    //Service[] getServices();

    Collection<Service> getServices(ServiceTypes type);

    List<Service> sortedServicesByCost();

    double cost();

    String toString();

    int hashCode();

    boolean equals(Object obj);

    public Tariff clone() throws CloneNotSupportedException;
}
