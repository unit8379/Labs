package rpis82.ezhov.oop.model;

import java.util.Arrays;

// тарифф юрид. лица. оперирует двусвязным списком. узлы описаны в классе Node
public class EntityTariff implements Tariff {
    private int size = 0; // здесь сайз показывает количество узлов в списке. некоторые могут быть не заполнены
    private Node head;
    private Node tail;

    // конструктор по умолчанию. инициализация головы и хвоста списка
    public EntityTariff() {
        this.head = new Node(null);
        this.tail = new Node(null, null, head);
        head.setNext(tail);
        size += 2;
    }

    // конструктор, заполняющий двусвязный список массивом услуг
    public EntityTariff(Service[] services) {
        this();
        for (Service service : services) {
            addNode(service);
        }
    }

    /**
     * Добавляет ссылку на экземпляр класса в список. При помощи приватного метода
     * @param service ссылка на экземпляр класса rpis82.ezhov.oop.Service
     * @return true, после завершения операции
     */
    public boolean add(Service service) {
        addNode(service);
        return true;
    }

    /**
     * Добавление узла (с его заполнением) в конец списка, лиюо в первый незаполненный узел
     * @param service услуга, которая пойдёт в value последнего не заполненного узла
     *                или нового узла
     */
    private void addNode(Service service) {
        if (head.getValue() == null) {
            head.setValue(service);  // заполняем голову если она была пуста
        }
        else {  // здесь создаём новый узел и его заполняем. если нашёлся узел со значением null, то заполняем его
            // но если всё верно то, это произойдёт лишь один раз при заполнении хвоста
            boolean isValueSet = false;
            Node currentNode = head; // текущий обрабатываемый узел
            //Node previousNode = currentNode.getPrevious();
            while (isValueSet != true) {
                if (currentNode.getNext() == null) {
                    currentNode.setNext(new Node(service, null, currentNode));
                    currentNode = currentNode.getNext();
                    tail = currentNode;
                    //previousNode = currentNode.getPrevious();
                    isValueSet = true;
                    size++;  // +1 узел
                    break;
                }

                if (currentNode.getNext().getValue() == null) {
                    currentNode.getNext().setValue(service);
                    tail = currentNode.getNext();
                    isValueSet = true;
                }

                currentNode = currentNode.getNext();
            }
        }
    }

    /**
     * Создаёт узел списка по заданному индексу и заполняет
     * его заданной ссылкой на экземпляр услуги
     * @param index индекс элемента для записи
     * @param service экземпляр класса
     * @return true, после завершения операции
     */
    public boolean add(Service service, int index) {
        addNode(service, index);
        return true;
    }

    /**
     * Возвращает ссылку на экземпляр класса по его номеру в списке
     * @param index номер узла
     * @return ссылка на экземпляр класса rpis82.ezhov.oop.Service
     */
    public Service get(int index) {
        return getNode(index);
    }

    /**
     * Возвращает ссылку на экземпляр класса по его атрибуту имени
     * @param serviceName искомый атрибут name
     * @return ссылка на экземпляр класса rpis82.ezhov.oop.Service
     */
    public Service get(String serviceName) {
        for (int i = 0; i < size; i++) {
            if (compareNames(i, serviceName)) {
                return getNode(i);
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
        for (int i = 0; i < size; i++) {
            return compareNames(i, serviceName);
        }
        return false;
    }

    private boolean compareNames(int index, String serviceName) {
        return getNode(index).getName().equals(serviceName);
    }

    /**
     * Заменяет ссылку в узле списка на новую. Старую возвращает.
     * @param service ссылка на новую ссылку
     * @param index номер элемента
     * @return предыдущая ссылка на экземпляр класса rpis82.ezhov.oop.Service
     */
    public Service set(Service service, int index) {
        Service currentService = getNode(index);
        changeNode(index, service);
        return currentService;
    }

    /**
     * Метод возвращает ссылку на экземпляр услуги узла,
     * который нужен по соответствующему индексу.
     * @param index индекс узла
     * @return экземпляр услуги
     */
    private Service getNode(int index) {
        Node currentNode = head;
        for (int i = 0; i <= index; i++) {
            if (i == index) {
                return currentNode.getValue();
            }
            currentNode = currentNode.getNext();
        }
        return null;
    }

    /**
     * Метод, изменяющий ссылку на услгу в узле под заданным индексом.
     * @param index индекс узла
     * @param service ссылка на экземпляр услуги
     */
    private void changeNode(int index, Service service) {
        Node currentNode = head;
        for (int i = 0; i <= index; i++) {
            if (i == index) {
                currentNode.setValue(service);
            }
            currentNode = currentNode.getNext();
        }
    }

    /**
     * Удаляет узел по заданному индексу из списка, возвращает
     * ссылку на экземпляр услуги, которая была в этом узле.
     * @param index индекс элемента
     * @return экземпляр класса, лежащий в этом узле
     */
    public Service remove(int index) {
        return removeNode(index).getValue();
    }

    /**
     * Метод удаляющий узел списка по его индексу.
     * Работа заключается в том, что на этот узел перестают ссылаться и
     * он удаляется мусоросборщиком. Перед этим метод возвращает этот узел.
     * Пока далее на возвращаемый узел будут ссылаться, он будет оставаться в памяти.
     * @param index индекс удаляемого узла
     * @return Node удалённый узел
     */
    private Node removeNode(int index) {
        Node currentNode = head;
        for (int i = 0; i <= index; i++) {
            if (i == index) {
                if (currentNode.getPrevious() != null) {
                    currentNode.getPrevious().setNext(currentNode.getNext());
                }
                if (currentNode.getNext() != null) {
                    currentNode.getNext().setPrevious(currentNode.getPrevious());
                }
                size--;
                return currentNode;
                // на currentNode больше никто не ссылается в рамках списка (т.е. удалён)
            }
            currentNode = currentNode.getNext();
        }
        return null;
    }

    /**
     * Удаляет узел, хранящий услгу с заданным именем.
     * Перед этим возвращает хранимую в том узле ссылку
     * на экземпляр услуги.
     * @param serviceName значение Name в экземпляре
     * @return экземпляр класса, лежащий в этом узле
     */
    public Service remove(String serviceName) {
        for (int i = 0; i < size; i++) {
            if (compareNames(i, serviceName)) {
                return removeNode(i).getValue();
            }
        }
        return null;
    }

    public int size() { return size; }

    /**
     * Удаляет экземпляр rpis82.ezhov.oop.Service из списка,
     * если он эквивалентен той ссылке, что передана в качесвте параметра.
     * @param service ссылка на услугу
     * @return логическое значение, показывающее была ли удалена ссылка
     */
    public boolean remove(Service service) {
        return removeNode(service);
    }

    /**
     * Метод удаляющий узел списка, если его услуга
     * идентична переданной в качестве параметра.
     * Работа заключается в том, что на этот узел перестают ссылаться и
     * он удаляется мусоросборщиком. Метод возвращает логическое
     * значение успешности его работы.
     * Пока далее на возвращаемый узел будут ссылаться, он будет оставаться в памяти.
     * @param service ссылка на услугу для сравнения.
     * @return Node удалённый узел.
     */
    private boolean removeNode(Service service) {
        Node currentNode = head;
        for (int i = 0; i < size; i++) {
            if (currentNode.getValue().equals(service)) {
                if (currentNode.getPrevious() != null) {
                    currentNode.getPrevious().setNext(currentNode.getNext());
                }
                if (currentNode.getNext() != null) {
                    currentNode.getNext().setPrevious(currentNode.getPrevious());
                }
                size--;
                return true;
                // на currentNode больше никто не ссылается в рамках списка (т.е. удалён)
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

    /**
     * Метод возвращающий индекс первого вхождения заданного
     * значения узла связанного списка.
     * @param service Ссылка на услугу для сравнения со значением узла.
     * @return Индекс первого вхождения или -1, если нужный узел не найден.
     */
    public int indexOf(Service service) {
        Node currentNode = head;
        for (int i = 0; i < size; i++) {
            if (currentNode.getValue().equals(service)) {
                return i;
            }
            currentNode = currentNode.getNext();
        }
        return -1;
    }

    /**
     * Метод возвращающий индекс последнего вхождения заданного
     * значения узла связанного списка.
     * @param service Ссылка на услугу для сравнения со значением узла.
     * @return Индекс последнего вхождения или -1, если нужный узел не найден.
     */
    public int lastIndexOf(Service service) {
        Node currentNode = tail;
        for (int i = size - 1; i < 0; i--) {
            if (currentNode.getValue().equals(service)) {
                return i;
            }
            currentNode = currentNode.getPrevious();
        }
        return -1;
    }

    /**
     * Возвращает массив всех услуг, содержащихся в двусвязном списке, пропуская
     * узлы содержащие значение null.
     * @return массив услуг (экземпляров класса rpis82.ezhov.oop.Service)
     */
    public Service[] getServicesWithoutNulls() {
        // в это части считаем сколько узлов без null
        int arrayWithoutNullsSize = 0;
        for (int i = 0; i < size; i++) {
            if (getNode(i) != null) {
                arrayWithoutNullsSize++;
            }
        }

        // заполняем массив для возврата
        Service[] arrayToReturnWithoutNulls = new Service[arrayWithoutNullsSize];
        int nodeFinder = 0;
        for (int i = 0; i < arrayToReturnWithoutNulls.length; i++) {
            for (; nodeFinder < size; nodeFinder++) {
                if (getNode(nodeFinder) != null) {
                    arrayToReturnWithoutNulls[i] = getNode(nodeFinder);
                    nodeFinder++;
                    break;
                }
            }
        }
        return arrayToReturnWithoutNulls;
    }

    public Service[] getServices() {
        return getServicesWithoutNulls();
    }

    /**
     * Массив сортируется по возрастанию стоимости услуг (пузырьком)
     * и возвращается.
     * @return массив услуг
     */
    public Service[] sortedServicesByCost() {
        Service[] arrayToReturnWithoutNulls = getServicesWithoutNulls();

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

    public Service[] getServices(ServiceTypes type) {
        Service[] services = getServicesWithoutNulls();

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
     * Возвращает общую стоимость тарифа.
     * 50 - сбор за обслуживание.
     * @return стоимость тарифа
     */
    public double cost() {
        double sumServicesCost = 0;
        for (Service element : getServicesWithoutNulls()) {
            sumServicesCost += element.getCost();
        }
        return sumServicesCost + 50;
    }

    // Ниже приватные методы, реализующие работу с двусвязным списком

    /**
     * Добавление узла (с его заполнением) в определённую позицию списка.
     * В цикле оператора while перемещаем все узлы от нужного индекса на один
     * узел вправо, чтобы появилось место для нового узла, а затем
     * устанавливаем новое значение в свободную позицию.
     * @param index позиция, на которую должен встать новый узел
     * @param service передаваемый экземпляр услуги
     */
    private void addNode(Service service, int index) {
        int i = 0;
        int startedSize = size;
        Node currentNode = tail;  // 5size index 1.  4nodetail -> 5nodetail 3 -> 4  2 -> 3

        // добавление узла, если нужно установить в самый конец
        if (i == size - index) {
            addNode(service); // устанавливаем экземпляр услуги там, где нужно
        }

        // добавление узла, если он в внутри списка
        while (i != startedSize - index) {
            currentNode.setNext(new Node(currentNode.getValue(), currentNode.getNext(), currentNode.getPrevious()));
            currentNode = currentNode.getPrevious();
            i++;
            if (i == startedSize - index) {
                // на случай если индекс равен 0
                // (т.к. не получиться добавить от предыдущего и поэтому
                // пока что будет ставить только в конец списка методом addNode())
                if (currentNode == null) { addNode(service); break;}

                currentNode.getNext().setValue(service); // устанавливаем экземпляр услуги там, где нужно
                tail = tail.getNext(); // обновляем хвост после сдвига
                size++;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Services:");
        for (Service element : getServicesWithoutNulls()) {
            stringBuilder.append(String.format("%n%s", element.toString()));
        }
        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        int code = 71;
        for (Service element : getServicesWithoutNulls()) {
            code *= element.hashCode();
        }
        return code < 0 ? -code : code;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        EntityTariff entityTariff = (EntityTariff) obj;
        return (this.size == entityTariff.size) && Arrays.equals(getServicesWithoutNulls(), entityTariff.getServicesWithoutNulls());
    }

    @Override
    public Tariff clone() throws CloneNotSupportedException {
        // глубокое клонирование
        // при клонировании из супер класса падает с CloneNotSupportedException, т.к. не может склонировать узлы списка.
        // здесь это делать необязательно, т.к. клон из констркутора получает поле size = 2, а потом во время
        // добавления услуг это число становится таким же как и у оригинала.
        Tariff tariff = new EntityTariff();

        for (Service element : getServicesWithoutNulls()) {
            tariff.add(element.clone());
        }
        return tariff;
    }
}