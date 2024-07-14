import java.util.ArrayList;

abstract class SchoolBell {
    public SchoolBell(int _intClock1) {
        _intClock = _intClock1;
    }

    //Register the Observers
    public void Attach(MATH math) {
        maths.add(math);
    }

    //Unregister from the list of Observers.
    public void Detach(MATH investor) {
        for (int i = 0; i < maths.size(); i++) {
            if (maths.get(i).get_classNO() == investor.get_classNO()) {
                maths.remove(i);
                return;
            }
        }
    }

    //Notify the Observers.
    public void Notify() {
        // set argument to something that helps
        // tell the Observers what happened
        for (int i = 0; i < maths.size(); i++) {
            if (maths.get(i).get_clock_state() == _intClock)
                maths.get(i).Update(this);
        }
    }

    public int get_intClock() {
        return _intClock;
    }

    abstract public void set_intClock(int value);

    protected int _intClock;
    protected ArrayList<MATH> maths = new ArrayList<MATH>();
}

//'ConcreteSubject' ==> IBM
class Hababam extends SchoolBell {

    public Hababam(int _intClock1) {
        super(_intClock1);
    }

    @Override
    public void set_intClock(int value) {
        _intClock = value;
        Notify();
    }

    public int get_intClock() {
        return _intClock;
    }
}

class DingDong extends SchoolBell {
    // Constructor
    public DingDong(int intClock1) {
        super(intClock1);
    }

    @Override
    public void set_intClock(int value) {
        _intClock = value;
        Notify();
    }

    public int get_intClock() {
        return _intClock;
    }

}

//'Observer'  ==> Abstract Observer.

interface Classroom {
    void Update(SchoolBell schoolBell);
}

//'ConcreteObserver' ==> Investor

class MATH implements Classroom {
    private SchoolBell _schoolBell;
    private final String _classNO;
    private int _clock_state;     // Internal Observer state

    // Constructor
    public MATH(String name) {
        _classNO = name;
    }

    public void Update(SchoolBell schoolBell) {
        _schoolBell = schoolBell;            // Reference to Subject
        System.out.println("Notified class: " + _classNO + ". @Clock: XX:" + schoolBell.get_intClock());
    }

    public SchoolBell get_schoolBell() {
        return _schoolBell;
    }

    public void set_schoolBell(SchoolBell value) {
        _schoolBell = value;
    }

    public int get_clock_state() {
        return _clock_state;
    }

    public void set_clock_state(int _clock_state) {
        this._clock_state = _clock_state;
    }

    public String get_classNO() {
        return _classNO;
    }
}

//MainApp test application
public class ObserverPattern {
    public static void main(String[] args) {
        // Create investors
        MATH a = new MATH("101");
        MATH b = new MATH("102");
        MATH c = new MATH("103");
        MATH d = new MATH("104");

        a.set_clock_state(20);
        b.set_clock_state(20);
        c.set_clock_state(50);
        d.set_clock_state(50);

        // Create IBM stock and attach investors
        DingDong dingDong = new DingDong(0);
        Hababam hababam = new Hababam(0);
        dingDong.Attach(a);
        dingDong.Attach(b);
        hababam.Attach(c);
        hababam.Attach(d);
        // Change price, which notifies investors
        for (int i = 0; i < 60; i++) {
            dingDong.set_intClock(i);
        }

        for (int i = 0; i < 60; i++)
            hababam.set_intClock(i);
    }
}