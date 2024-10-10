//Name: Yumna Khan ID: YK220040

public class Driver implements Comparable<Driver> {
    private String name;
    private double area;
    private static String comparison = "name";

    public Driver() {
        //default constructor
    }

    public Driver(String name, double area) {
        this.name = name;
        this.area = area;
    }

    //method to get name
    public String getName() {
        return name;
    }

    //method to get area
    public double getArea() {
        return area;
    }

    //method to get comparison
    public static String getComparison() {
        return comparison;
    }

    //method to set comparison
    public static void setComparison(String value) {
        comparison = value;
    }

    @Override
    public int compareTo(Driver other){
        if (comparison.equals("name")){ //compare based on name
            return this.name.compareTo(other.getName());
        }
        else if (comparison.equals("area")){ //compare based on area values
            return Double.compare(this.area, other.getArea());
        }
        else {
            return 0;
        }
    }
    
    //custom toString method
    @Override
    public String toString(){
        return "[Name: " + name + ", Area: " + area + "]";
    }
}
