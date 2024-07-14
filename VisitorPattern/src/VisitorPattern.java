import java.util.ArrayList;
import java.util.Random;
interface Visitor{
    void Visit(House h);
    void Visit(Apt a);
}
abstract class Worker implements Visitor{
    protected String _name;
    protected int _salary;
    protected int _tmpLock;

    public Worker(String name){
        this._name = name;
        this._salary = 0;
    }
    public void set_tmpLock(int _tmpLock) {
        this._tmpLock = _tmpLock;
    }

    public void set_salary(int _salary) {
        this._salary = _salary;
    }

    public int get_salary() {
        return _salary;
    }

    abstract void getLock(Place p);
    abstract void doJob(Place p);
}
class Plumber extends Worker{
    public Plumber(String name){
        super(name);
    }
    @Override
    void doJob(Place p) {
        p.setWJob(true);
    }

    @Override
    void getLock(Place p) {
        set_tmpLock(p.get_lock());
    }

    @Override
    public void Visit(House house){
        getLock(house);
        System.out.println("Plumber has acquired the Lock from House. Lock: " + house.get_lock());;
        doJob(house);
        this.set_salary(this.get_salary()+500);
        System.out.println("New Salary: "+ this.get_salary());
        System.out.println("Now leaving the House...");
        this.set_tmpLock(-1);
    }

    @Override
    public void Visit(Apt a) {
        getLock(a);
        System.out.println("Plumber has acquired the Lock form Apt. Lock: " + a.get_lock());;
        doJob(a);
        this.set_salary(this.get_salary()+250);
        System.out.println("New Salary: "+ this.get_salary());
        System.out.println("Now leaving the Apt...");
        this.set_tmpLock(-1);
    }
}
class Electrician extends Worker{
    public Electrician(String name){
        super(name);
    }
    @Override
    void doJob(Place p) {
        p.setEJob(true);
    }

    @Override
    void getLock(Place p) {
        set_tmpLock(p.get_lock());
    }

    @Override
    public void Visit(Apt a) {
        getLock(a);
        System.out.println("Electrician has acquired the Lock form Apt. Lock: " + a.get_lock());;
        doJob(a);
        this.set_salary(this.get_salary()+100);
        System.out.println("New Salary: "+ this.get_salary());
        System.out.println("Now leaving the Apt...");
        this.set_tmpLock(-1);
    }

    @Override
    public void Visit(House h) {
        getLock(h);
        System.out.println("Electrician has acquired the Lock from House. Lock: " + h.get_lock());;
        doJob(h);
        this.set_salary(this.get_salary()+200);
        System.out.println("New Salary: "+ this.get_salary());
        System.out.println("Now leaving the House...");
        this.set_tmpLock(-1);
    }
}
interface Element{
    void Accept(Visitor visitor);
}
abstract class Place implements Element{
    public String _name;
    protected boolean eJob;
    protected boolean wJob;
    private int _lock;

    public Place(String name){
        this._name = name;
        this.eJob = false;
        this.wJob = false;
    }

    public String getName() {
        return _name;
    }

    public void setEJob(boolean eJob) {
        this.eJob = eJob;
    }

    public void setWJob(boolean wJob) {
        this.wJob = wJob;
    }

    public void set_lock(int _lock) {
        this._lock = _lock;
    }

    public int get_lock() {
        return _lock;
    }
}
class Apt extends Place{
    Random random = new Random();
    public Apt(String name){
        super(name);
        set_lock(random.nextInt(1000));
        System.out.println(get_lock());
    }

    @Override
    public void Accept(Visitor visitor) {
        visitor.Visit(this);
    }
}
class House extends Place{
    Random random = new Random();
    public House(String name){
        super(name);
        set_lock(random.nextInt(100)+1000);
        System.out.println(get_lock());
    }

    @Override
    public void Accept(Visitor visitor) {
        visitor.Visit(this);
    }
}
class Places{
    public void Add(Place place){
        places.add(place);
    }
    public void Remove(Place place){
        for (int i = 0; i< places.size(); i++) {
            if (places.get(i).getName() == place.getName()) {
                places.remove(i);
                return;
            }
        }
    }
    public void Accept(Visitor visitor){
        for(Place p : places){
            p.Accept(visitor);
        }
    }
    private ArrayList<Place> places = new ArrayList<>();
}
public class VisitorPattern {
    public static void main(String[] args) {
        Places p = new Places();
        p.Add(new House("House1"));
        p.Add(new Apt("Apt1"));

        p.Accept(new Electrician("Electrician1"));
        p.Accept(new Plumber("Plumber"));

    }
}