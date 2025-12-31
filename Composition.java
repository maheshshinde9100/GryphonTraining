class Engine{
    void start(){
        System.out.println("Car is started...");
    }
}
class Car{
    Engine eng;
    Car(){
        eng = new Engine();
    }
    void drive(){
        eng.start();
        System.out.println("Car is driving...");
    }
}

public class Composition {
    public static void main(String[] args) {
        Car c = new Car();
        c.drive();
    }
}
