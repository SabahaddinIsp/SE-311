import java.util.ArrayList;

// Top "Abstract Product" Part class
abstract class Part {
    abstract public String displayName();
    abstract double getPrice();
}
// An 'Abstract Product A' class
// Engine base class.
abstract class Chair extends Part {
    protected double price;
    protected String name;
    public double getPrice(){ return price; }
    public String displayName() { return name; }
}

class Future_Chair extends Chair{
    public Future_Chair(double p){
        price = p;
        name = new String("Future Now Chair");
        System.out.println("Future Now Chair");
    }
}

//A 'ConcreteProduct A1' class
class ArtCom_Chair extends Chair {
    public ArtCom_Chair(double p) {
        price = p;
        name = new String("Art Company Chair");
        System.out.println("Art Company Chair");
    }
}
//A 'ConcreteProduct A2' class
class History_Chair  extends Chair {
    public History_Chair(double p) {
        price = p;
        name = new String("History Company Chair");
        System. out.println("History Company Chair is created...");
    }
}
//An 'Abstract Product B' class
//Transmission base class
abstract class Table extends Part {
    protected double price;
    protected String name;
    public double getPrice(){ return price; }
    public String displayName() { return name; }
}

class Future_Table extends Table{
    public Future_Table(double p){
        price = p;
        name = new String("Future Now Table");
        System.out.println("Future Now Table is created...");
    }
}
//A 'ConcreteProduct B1' class
class ArtCom_Table extends Table {
    public ArtCom_Table(double p) {
        price = p;
        name = new String("Art Company Table");
        System. out.println("Art Company Table is created...");
    }
}
//A 'ConcreteProduct B2' class
class History_Table extends Table {
    public History_Table(double p) {
        price = p;
        name = new String("History Company Table");
        System. out.println("History Company Table is  created...");
    }
}
//An 'Abstract Factory' class
abstract class FurnitureFactory {
    abstract public Chair createChair();
    abstract public Table createTable();
    abstract public Part createAll(int choice);
}

class FutureNow extends FurnitureFactory{
    @Override
    public Part createAll(int tmp) {
        if(tmp==0)
            return createChair();
        if(tmp==1)
            return createTable();
        return null;
    }

    public Future_Chair createChair(){
        return new Future_Chair(550);
    }
    public Future_Table createTable(){
        return new Future_Table(650);
    }
}

//A 'Concrete Factory' class
class ArtCom extends FurnitureFactory {
    public Part createAll(int tmp) {
        if(tmp==0)
            return createChair();
        if(tmp==1)
            return createTable();
        return null;
    }
    public ArtCom_Chair createChair() {
        return new ArtCom_Chair (600);
    }
    public ArtCom_Table createTable() {
        return new ArtCom_Table(1100);
    }

}

//Another 'Concrete Factory' class
class History extends FurnitureFactory {
    public Part createAll(int tmp) {
        if(tmp==0)
            return createChair();
        if(tmp==1)
            return createTable();
        return null;
    }
    public History_Chair createChair() {
        return new History_Chair (500);
    }
    public History_Table createTable() {
        return new History_Table(1000);
    }

}

//The 'Client'.
class BuildBundle {
    // Object creation is delegated to factory.
    public void BuildBundle(FurnitureFactory factory) {
        parts = new ArrayList<Part>();
        parts.add(factory.createAll(0));
        parts.add(factory.createAll(1));
    }
    void displayProducts() {
        System.out.println("\tListing Products\n\t-------------");
        parts.forEach(p  -> System.out.println("\t"+ p.displayName() +
                " " + p.getPrice()));
    }
    private ArrayList<Part> parts;
}
//Abstract Factory Method Design Pattern.
//Entry point into main application.
public class AbstractFactoryPattern {
    public static void main(String[] args) {
        // Create factories.
        FurnitureFactory artCom = new ArtCom();
        FurnitureFactory history = new History();
        FurnitureFactory future = new FutureNow();

        BuildBundle bundle = new BuildBundle();
        System.out.println("Creating Art Company Bundle");
        bundle.BuildBundle(artCom);
        bundle.displayProducts();

        System.out.println("Creating History Company Bundle");
        bundle.BuildBundle(history);
        bundle.displayProducts();

        System.out.println("Creating Future Now Bundle");
        bundle.BuildBundle(future);
        bundle.displayProducts();
    }
}