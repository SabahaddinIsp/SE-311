import java.util.ArrayList;

interface HepsiTrendy11 {
    void display(int indent);

    void add(HepsiTrendy11 hepsiTrendy11);

    void remove(HepsiTrendy11 hepsiTrendy11);

    String getName();

    boolean isFind(String name);
}


class Product implements HepsiTrendy11 {
    private final String name;
    private final int price;
    private String description;

    public Product(String name1, int price1) {
        this.name = name1;
        this.price = price1;
    }

    @Override
    public void display(int indent) {
        for (int i = 1; i <= indent; i++) System.out.print("-");
        System.out.println(" " + name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void add(HepsiTrendy11 hepsiTrendy11) {
        System.out.println("Cannot add");
    }

    @Override
    public void remove(HepsiTrendy11 hepsiTrendy11) {
        System.out.println("Cannot remove");
    }

    @Override
    public boolean isFind(String name1) {
        return this.name.equals(name1);
    }
}

class Category implements HepsiTrendy11 {
    private final String name;

    public Category(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void add(HepsiTrendy11 hepsiTrendy11) {
        elements.add(hepsiTrendy11);
    }

    @Override
    public void remove(HepsiTrendy11 hepsiTrendy11) {
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getName() == hepsiTrendy11.getName()) {
                elements.remove(i);
                return;
            }
        }
    }

    @Override
    public boolean isFind(String tmpName) {
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getName().equals(tmpName)) {
                return true;
            }
            if (elements.get(i).isFind(tmpName)) {
                return true;
            }

        }
        return false;
    }

    @Override
    public void display(int indent) {
        for (int i = 1; i <= indent; i++) System.out.print("-");
        System.out.println("+ " + getName());
        for (int i = 0; i < elements.size(); i++) {
            elements.get(i).display(indent + 2);
        }
    }

    ArrayList<HepsiTrendy11> elements = new ArrayList<HepsiTrendy11>();
}

public class CompositePattern {
    public static void main(String[] args) {
        HepsiTrendy11 root = new Category("All");
        HepsiTrendy11 electronics = new Category("Eletronics");
        HepsiTrendy11 tv = new Category("Tv");
        tv.add(new Product("OLED TV", 20));
        tv.add(new Product("QLED TV", 24));
        HepsiTrendy11 pc = new Category("PC");
        pc.add(new Product("RAM", 5));
        pc.add(new Product("SSD", 7));
        electronics.add(tv);
        electronics.add(pc);

        HepsiTrendy11 fashion = new Category("Fashion");
        HepsiTrendy11 men = new Category("MEN");
        HepsiTrendy11 women = new Category("WOMEN");
        men.add(new Product("Suit", 7));
        women.add(new Product("Skirt", 8));
        women.add((new Product("Shirt", 2)));
        fashion.add(men);
        fashion.add(women);

        HepsiTrendy11 outdoor = new Category("OUTDOOR");
        outdoor.add(new Product("Tent", 15));

        HepsiTrendy11 cosmetic = new Category("COSMETICS");
        HepsiTrendy11 skinCare = new Category("Skin Care");
        skinCare.add(new Product("Face Cream", 10));
        skinCare.add(new Product("Sun Protector", 20));
        cosmetic.add(skinCare);
        cosmetic.add(new Product("Shampoo", 7));
        cosmetic.add(new Product("Parfum", 5));


        root.add(electronics);
        root.add(fashion);
        root.add(outdoor);
        root.add(cosmetic);
        System.out.println(root.isFind("OUTDOOR"));

    }
}
