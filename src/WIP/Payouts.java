package wip;

import java.util.ArrayList;

public class Payouts {
    protected Variant currenVariant;
    protected ArrayList<Variant> variants;

    /**
     * Default constructor for the class Payouts, it creates the ArrayList of
     * variants as well as the default variant "Double Bonus 10/7"
     */
    public Payouts() {
        variants = new ArrayList<Variant>();
        currenVariant = new Variant();
        variants.add(currenVariant);
    }

    /**
     * Adds a new game variant to the list of variants based on a input file. It
     * also switches the current variant to the new one.
     * 
     * @param name     Name of the game variant
     * @param filename Path to the input file
     */
    public void addVariantFile(String name, String filename) {
        Variant v = new Variant(name, filename);
        this.currenVariant = v;
        this.variants.add(v);
    }

    /**
     * Adds a new game variant to the list of variants based on a passed array. It
     * also switches the current variant to the new one.
     * 
     * @param name   Name of the game variant
     * @param values 2D Array with the values of the payouts for each hand
     */
    public void addVariantArray(String name, int[][] values) {
        // ## Add prevention for duplicates throw an exception!!

        Variant v = new Variant(name, values);
        this.currenVariant = v;
        this.variants.add(v);
    }

    /**
     * Changes the current game variant to the name with the name passed as an
     * argument.
     * 
     * @param name Name of the game variant
     */
    public void setVariant(String name) {
        for (Variant v : variants) {
            if (v.name.equals(name)) {
                this.currenVariant = v;
            }
        }
    }

    @Override
    public String toString() {
        return this.currenVariant.toString();
    }

    public String printList() {
        String s = "";
        for (Variant v : variants) {
            s += v.toString();
        }
        return s;
    }

    public static void main(String[] args) {
        // Payout tests
        Payouts p = new Payouts();

        p.addVariantFile("Double Bonus 69", "files/variant10-7.txt");
        p.addVariantFile("Double Bonus 88", "files/variant10-7.txt");

        p.setVariant("Double Bonus 69");
        System.out.println(p);
        System.out.println("###################");
        System.out.println(p.printList());
    }
}
