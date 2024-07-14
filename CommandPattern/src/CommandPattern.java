import java.util.ArrayList;

//"Command"
interface Task {
    public void Execute();
    public void UnExecute();
}
//"ConcreteCommand"
class MakeGreen implements Task {
    // Constructor
    public MakeGreen(CPU cpu, String color) {
        _cpu = cpu;
        _color = color;
    }
    public void Execute() {
        _cpu.Action(_color);
    }
    public void UnExecute() {
        _cpu.Action(Undo());
    }

    // Private helper function. Needed to get the inverse operation.
    private String  Undo() {
        return _cpu.getPrevious_color();
    }
    private CPU _cpu;
    private String _color;
}

class MakeYellow implements Task {
    // Constructor
    public MakeYellow(CPU cpu, String color) {
        _cpu = cpu;
        _color = color;
    }
    public void Execute() {
        _cpu.Action(_color);
    }
    public void UnExecute() {
        _cpu.Action(Undo());
    }

    // Private helper function. Needed to get the inverse operation.
    private String  Undo() {
        return _cpu.getPrevious_color();
    }
    private CPU _cpu;
    private String _color;
}
class MakeRed implements Task {
    // Constructor
    public MakeRed(CPU cpu,String color) {
        _cpu = cpu;
        _color = color;
    }
    public void Execute() {
        _cpu.Action(_color);
    }
    public void UnExecute() {
        _cpu.Action(Undo());
    }

    // Private helper function. Needed to get the inverse operation.
    private String  Undo() {
        return _cpu.getPrevious_color();
    }
    private CPU _cpu;
    private String _color;
}
class MakeBlue implements Task {
    // Constructor
    public MakeBlue(CPU cpu, String color) {
        _cpu = cpu;
        _color = color;
    }
    public void Execute() {
        _cpu.Action(_color);
    }
    public void UnExecute() {
        _cpu.Action(Undo());
    }

    // Private helper function. Needed to get the inverse operation.
    private String  Undo() {
        return _cpu.getPrevious_color();
    }
    private CPU _cpu;
    private String _color;
}
// "Receiver"
//
class CPU {
    public CPU() {
        screen_color = "";
        previous_color = "";
    }
    public void Action(String tmpColor) {
        previous_color = screen_color;
        System.out.println("The Screen Now Shows "+ screen_color);
        screen_color = tmpColor;
    }

    public String getPrevious_color() {
        return previous_color;
    }

    private String previous_color;
    private String screen_color;
}

// "Invoker"
class TaskScheduler {
    public TaskScheduler() {   current = 0; }
    public void Redo(int levels) {
        System.out.println("\n---- Redo " + levels + " levels ");
        // Perform redo operations
        for (int i = 0; i < levels; i++) {
            if (current < _task.size()) {
                Task command = _task.get(current++);
                command.Execute();
            }
        }
    }

    void Undo(int levels) {
        System.out.println("\n---- Undo " + levels + " levels ");
        // Perform undo operations
        for (int i = 0; i < levels; i++) {
            if (current > 0) {
                Task task = _task.get(--current);
                task.UnExecute();
            }
        }
    }
    void Compute(Task task) {
        task.Execute();
        // Add command to undo list
        _task.add(task);
        current++;
    }

    // Initializers.
    private int current;
    private ArrayList<Task> _task = new ArrayList<Task>();
};

public class CommandPattern {
    public static void main(String[] args) {

        // Create user and let her compute
        Task task = null;
        TaskScheduler user = new TaskScheduler();
        CPU cpu = new CPU();

        task = new MakeRed(cpu,"red");
        user.Compute(task);
        task = new MakeBlue(cpu,"blue");
        user.Compute(task);
        task = new MakeYellow(cpu, "yellow");
        user.Compute(task);
        task = new MakeGreen(cpu, "green");
        user.Compute(task);

        /*
        // Undo 4 commands
        user.Undo(4);
        // Redo 2 commands
        user.Redo(2);

         */
    }
}