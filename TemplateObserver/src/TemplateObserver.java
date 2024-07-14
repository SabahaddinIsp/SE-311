import java.util.ArrayList;
import java.util.Random;

interface Subject {

    void attach(VaultComputer vaultComputer);

    void detach(VaultComputer vaultComputer);

    void Notify();
}

interface Observer {

    void Update(Device device1, Device device2);
}

// Subject
abstract class Device implements Subject {

    protected ArrayList<Observer> holder = new ArrayList();

    abstract void generateData();

    abstract void openHatch();

    public void hook() {
    }

    abstract void read();

    public double get__waData() {
        return 0;
    }

    public int get__wData() {
        return 0;
    }

    public void collectData() {

        generateData();

        openHatch();

        hook();

        read();

    }
}

// Concrete Subject
class WeatherInfoDevice extends Device {

    Random random;
    private int __wData;

    public int get__wData() {

        return __wData;

    }

    @Override
    void generateData() {

        random = new Random();

        __wData = random.nextInt(1000) + 1;

        Notify();

    }

    @Override
    void openHatch() {

        System.out.println("Weather Info OpenHatch");

    }

    @Override
    void read() {

        System.out.println("Weather Data collection is completed");

    }

    @Override

    public void attach(VaultComputer vaultComputer) {

        holder.add(vaultComputer);

    }

// We have one Vault so no need for search operation

    @Override

    public void detach(VaultComputer vaultComputer) {

        holder.clear();

    }

    @Override

    public void Notify() {

        holder.get(0).Update(this, this);

    }
}

// Concrete Subject
class WaterInfoDevice extends Device {

    Random random;
    private double __waData;

    public double get__waData() {

        return __waData;

    }

    @Override
    void generateData() {

        random = new Random();

        __waData = random.nextDouble();


        Notify();

    }

    @Override
    void openHatch() {

        System.out.println("Water Info OpenHatch");

    }

    @Override

    public void hook() {

        System.out.println("Dip in Water");

    }

    @Override
    void read() {

        System.out.println("Water Data collection is completed");

    }

    @Override

    public void attach(VaultComputer vaultComputer) {

        holder.add(vaultComputer);

    }

    @Override

    public void detach(VaultComputer vaultComputer) {

        holder.clear();

    }

    @Override

    public void Notify() {

        holder.get(0).Update(this, this);

    }
}

class VaultComputer implements Observer {

    protected int _wData;
    protected double _waData;
    private Device _weatherDevice;
    private Device _waterDevice;

    @Override

    public void Update(Device waterDevice, Device weatherDevice) {

        System.out.println("Device Updated");

        _waterDevice = waterDevice;

        _weatherDevice = weatherDevice;

        _wData = weatherDevice.get__wData();

        _waData = waterDevice.get__waData();

    }

    public void set_waterDevice(Device waterDevice) {

        this._waterDevice = waterDevice;

    }

    public void set_weatherDevice(Device weatherDevice) {

        this._weatherDevice = weatherDevice;

    }
}

class Player {

    private final Device _waterDev;

    private final Device _weatherDev;

    private final VaultComputer _vaultComputer;

    public Player(Device d1, Device d2, VaultComputer v1) {

        _waterDev = d1;

        _weatherDev = d2;

        _vaultComputer = v1;

        _waterDev.attach(_vaultComputer);

        _weatherDev.attach(_vaultComputer);

        _vaultComputer.set_weatherDevice(_weatherDev);

        _vaultComputer.set_waterDevice(_waterDev);

    }

    public void collect() {

        _weatherDev.collectData();

        _waterDev.collectData();

        _vaultComputer.Update(_waterDev, _weatherDev);

        System.out.println("Water: " + _waterDev.get__waData());

        System.out.println("Weather: " + _weatherDev.get__wData());

    }
}

public class TemplateObserver {

    public static void main(String[] args) {
        Device weather = new WeatherInfoDevice();
        Device water = new WaterInfoDevice();
        VaultComputer vaultComputer = new VaultComputer();
        Player p1 = new Player(water, weather, vaultComputer);
        p1.collect();
        p1.collect();
        p1.collect();
    }
}
