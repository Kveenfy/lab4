package by.fedorovich;

import java.io.*;

// Часть А
class Text{
    String value;
    Text(String value){
        this.value = value;
    }
}
class Paragraph{
    int Stings = 0;
    Text text;
    Paragraph(Text value){
        this.text = value;
    }
}

// Часть В
class Deport{
    Inner inner;
    void ShowDispatcher(){
        System.out.println(inner.dispatcher.name);
    }
    Deport(Dispatcher dispatcher,  Driver[] drivers, Car[] cars){
        inner = new Inner(dispatcher,drivers,cars);
    }
    class Inner{
        Dispatcher dispatcher;
        Car[] cars = new Car[3];
        Driver[] drivers = new Driver[3];
        Inner(Dispatcher dispatcher,  Driver[] drivers, Car[] cars){
            this.dispatcher = dispatcher;
            this.drivers = drivers;
            this.cars = cars;
        }
    }
}
class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    String nameOfWork;
    String name;
    Employee(String nameOfWork, String name){
        this.nameOfWork = nameOfWork;
        this.name = name;
    }
}
class Dispatcher extends Employee{
    Dispatcher(String nameOfWork, String name){
        super(nameOfWork,name);
    }
    void setTrip(Driver driver, int trip, Car car){
        driver.setTrip(trip);
        driver.setCar(car);
    }
    void setWorks(Driver driver ,boolean works){
        driver.setWorks(works);
    }

}
class Driver extends Employee{
    int trip;
    boolean tripComplete = false;
    Car car;
    boolean works = true;
    Driver(String nameOfWork, String name){
        super(nameOfWork,name);
    }
    public void setTrip(int trip) {
        this.trip = trip;
    }
    public void setCar(Car car){
        this.car = car;
    }
    public String getInfo(){
        return "Trip: " + trip + ", Car: " + car.showInfo() + ", Works: " + works;
    }
    void setRepair(boolean repair){
        this.car.setRepair(repair);
    }
    void setWorks(boolean works){
        this.works = works;
    }

    public void tripComplete(boolean tripComplete, boolean carStatus) {
        this.tripComplete = tripComplete;
        car.setStatus(carStatus);
    }
}

class Car{
    boolean repair;
    boolean status = true;
    private int id;
    private String brand;
    private String model;
    private int year;
    private String color;
    private double cost;
    private int number;
    Car(int idK, String brandK, String modelK, int yearK, String colorK, double costK, int numberK){
        id = idK;
        brand = brandK;
        model = modelK;
        year = yearK;
        color = colorK;
        cost = costK;
        number = numberK;
    }
    int getId(){
        return id;
    }
    String getBrand(){
        return brand;
    }
    String getModel(){
        return model;
    }
    int getYear(){
        return year;
    }
    String getColor(){
        return color;
    }
    double getCost(){
        return cost;
    }
    int getNumber(){
        return number;
    }
    void setRepair(boolean repair){
        this.repair = repair;
    }
    void setStatus(boolean status){
        this.status = status;
    }
    String showInfo(){
        return "id: " + id + ", brand: " + brand + ", model: " + model + ", year: " + year + ", color: "+ color + ", cost: "+ cost + ", number: "+ number + ", repair: "+ repair + ", status: "+ status;
    }
}
interface Pause{
    public void Print();
}

public class Main {
    public static void main(String[] args) throws IOException,ClassNotFoundException {
        //Часть А
        Text text = new Text("Hi");
        Paragraph paragraph = new Paragraph(text);
        //Часть B
        Dispatcher dispatcher = new Dispatcher("Диспетчер","Иван");
        Driver[] drivers = new Driver[3];
        drivers[0] = new Driver("Водитель","Михаил");
        drivers[1] = new Driver("Водитель","Кирилл");
        drivers[2] = new Driver("Водитель","Давид");
        Car[] cars = new Car[3];
        cars[0] = new Car(1,"BMW","I8",2015,"Black",100000,207587);
        cars[1] = new Car(2,"Tesla","X",2020,"White",120000,578557);
        cars[2] = new Car(3,"Mersedes","Maybach",2014,"Green",150000,766427);
        dispatcher.setTrip(drivers[0],5,cars[0]);
        dispatcher.setTrip(drivers[1],3,cars[1]);
        dispatcher.setTrip(drivers[2],4,cars[2]);
        System.out.println(drivers[2].getInfo());
        drivers[1].setRepair(true);
        System.out.println(drivers[1].car.repair);
        dispatcher.setWorks(drivers[2],false);
        System.out.println(drivers[2].getInfo());
        drivers[0].tripComplete(true,true);
        System.out.println(drivers[0].getInfo());
        Deport deport = new Deport(dispatcher,drivers,cars);
        deport.ShowDispatcher();
        Pause pause = new Pause(){
            public void Print(){
                System.out.println("Обеденный перерыв");
            }
        };
        pause.Print();
        Employee employee = new Employee("Водитель","Вадим");
        FileOutputStream outputStream = new FileOutputStream("save.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(employee);
        objectOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream("save.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Employee employee1 = (Employee) objectInputStream.readObject();
        System.out.println(employee1);
    }
}
