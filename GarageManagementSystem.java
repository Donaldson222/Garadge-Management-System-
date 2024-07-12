import java.util.*;

class Car {
    String make;
    String model;
    String licensePlate;

    public Car(String make, String model, String licensePlate) {
        this.make = make;
        this.model = model;
        this.licensePlate = licensePlate;
    }
}

class Mechanic {
    String name;
    LinkedList<Car> assignedCars;

    public Mechanic(String name) {
        this.name = name;
        this.assignedCars = new LinkedList<>();
    }

    @Override
    public String toString() {
        return name;
    }
}

class Garage {
    String[] carModels; // array to store car models
    Stack<Car> repairJobs; // stack to manage repair jobs
    Queue<Car> carArrivals; // queue to manage car arrivals
    LinkedList<Mechanic> mechanics; // linked list to store mechanic information
    TreeMap<String, Car> carTree; // tree to organize cars by make

    public Garage() {
        carModels = new String[100]; // initialize array with 100 slots
        repairJobs = new Stack<>();
        carArrivals = new LinkedList<>();
        mechanics = new LinkedList<>();
        carTree = new TreeMap<>();
    }

    public void addCar(String make, String model, String licensePlate) {
        Car car = new Car(make, model, licensePlate);
        carModels[carModels.length - 1] = model; // add car model to array
        carTree.put(make, car); // add car to tree
    }

    public void assignRepairJob(String licensePlate, String mechanicName) {
        Car car = getCarByLicensePlate(licensePlate);
        if (car != null) {
            repairJobs.push(car); // add car to stack
            Mechanic mechanic = getMechanicByName(mechanicName);
            if (mechanic != null) {
                mechanic.assignedCars.add(car); // add car to mechanic's linked list
            }
        }
    }

    public void completeRepairJob(String licensePlate) {
        Car car = getCarByLicensePlate(licensePlate);
        if (car != null) {
            repairJobs.pop(); // remove car from stack
            for (Mechanic mechanic : mechanics) {
                if (mechanic.assignedCars.contains(car)) {
                    mechanic.assignedCars.remove(car); // remove car from mechanic's linked list
                }
            }
        }
    }

    public void arriveCar(String licensePlate) {
        Car car = getCarByLicensePlate(licensePlate);
        if (car != null) {
            carArrivals.add(car); // add car to queue
        }
    }

    public Car getCarByLicensePlate(String licensePlate) {
        for (Car car : carTree.values()) {
            if (car.licensePlate.equals(licensePlate)) {
                return car; // retrieve car from tree
            }
        }
        return null;
    }

    public Mechanic getMechanicByName(String name) {
        for (Mechanic mechanic : mechanics) {
            if (mechanic.name.equals(name)) {
                return mechanic; // retrieve mechanic from linked list
            }
        }
        return null;
    }
}

public class GarageManagementSystem {
    public static void main(String[] args) {
        Garage garage = new Garage();

        // add cars
        garage.addCar("Toyota", "Camry", "ABC123");
        garage.addCar("Honda", "Civic", "DEF456");
        garage.addCar("Ford", "Mustang", "GHI789");

        // add mechanics
        garage.mechanics.add(new Mechanic("George Otieno"));
        garage.mechanics.add(new Mechanic("Jack Aluoch"));

        // assign repair jobs
        garage.assignRepairJob("ABC123", "George Otieno");
        garage.assignRepairJob("DEF456", "Jack Aluoch");

        // arrive cars
        garage.arriveCar("GHI789");

        // complete repair jobs
        garage.completeRepairJob("ABC123");

        // print repair jobs
        System.out.println("Repair Jobs:");
        for (Car car : garage.repairJobs) {
            System.out.println(car.licensePlate);
        }

        // print car arrivals
        System.out.println("Car Arrivals:");
        for (Car car : garage.carArrivals) {
            System.out.println(car.licensePlate);
        }

        // print mechanic information
        System.out.println("Mechanic Information:");
        for (Mechanic mechanic : garage.mechanics) {
            System.out.println(mechanic.name + ": " + mechanic.name);
        }
    }
}