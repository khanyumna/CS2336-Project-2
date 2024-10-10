// Name: Yumna Khan, netID: YXK220040

//import libraries
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[]args){
        

        //initialize scanner, get input files
        Scanner scanner = new Scanner(System.in); 
        System.out.print("Enter the filename for Driver Route File: ");
        String driverRouteFile = scanner.nextLine();

        System.out.print("Enter the filename for Search and Sort Information File: ");
        String searchSortFile = scanner.nextLine();
        scanner.close();

        //call methods
        MyLinkedList<Driver> drivers = new MyLinkedList<>();
        readDriverRouteFile(driverRouteFile,drivers);
        readSearchSortFile(searchSortFile,drivers);
    }

    //this method processes the search and sort file
    public static void readSearchSortFile(String filename, MyLinkedList<Driver> drivers){
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) { //read each line, split into parts
                String[] parts = line.split(" ");
                String command = parts[0];

                if (command.equals("sort")) { //sort the list of drivers
                    String criteria = parts[1]; 
                    String order = parts[2];
                    Driver.setComparison(criteria);
                    drivers.sort();
                } else if (command.equals("driver")) { //search for driver name
                    String driverName = parts[1];
                    MyLinkedList<Driver> driverList = drivers.searchName(driverName);
                    System.out.println(driverList.toString());
                } else if (command.equals("number")) { //search driver with matching area
                    double areaNumber = Double.parseDouble(parts[1]);
                    MyLinkedList<Driver> driverList = drivers.searchArea(areaNumber);
                    System.out.println(driverList.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //method for processing driver route file
    public static void readDriverRouteFile(String filename,MyLinkedList<Driver> drivers){

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) { //read each line, split into parts
                String[] parts = line.split(" ");
                String name = parts[0];
                LinkedList<Driver> coordinates = new LinkedList<>();

                for (int i = 1; i < parts.length; i++) { //get data and append to linked list of drivers
                    String[] xy = parts[i].split(",");
                    float x = Float.parseFloat(xy[0]);
                    float y = Float.parseFloat(xy[1]);
                   // coordinates.add(new Driver(name, calculateArea(x, y)));
                }

                // drivers.add(new Node<Driver>(new Driver(name, coordinates)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }


    //method for reading file data 
    public static int readData(String fileName, String pilotNames[], double[][][] coordinates){
        int allPilots = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            
            //filter through pilots, populate arrays
            while ((line = reader.readLine()) != null && allPilots < 20) {
                String[] parts = line.split(" ");
                pilotNames[allPilots] = parts[0];

                //set coordinates
                for (int i = 1; i < parts.length; i++) {
                    String[] coord = parts[i].split(",");
                    coordinates[allPilots][i-1][0] = Double.parseDouble(coord[0]); 
                    coordinates[allPilots][i-1][1] = Double.parseDouble(coord[1]);
                } allPilots++;
            } 
            } catch(IOException e){
                System.err.println("Error");
        } return allPilots;
    }
    
    //method for calculating the area
    public static double[] calculateArea(float x, float y){
        double[] allAreas = new double[y];
        for (int pilot = 0; pilot < y; pilot++){
            double [][] pilotCoords = x[pilot];
            double area = 0;
            
            //getting all coordinates to calculate area
            for (int i = 0; i < pilotCoords.length - 1; i++) {
                double x1 = pilotCoords[i][0];
                double x2 = pilotCoords[i][1];
                double y1 = pilotCoords[i + 1][0];
                double y2 = pilotCoords[i + 1][1];
                area += ((x1 * y2) - (x2 * y1)); //calculating area
            } 
            allAreas[pilot] = Math.abs(area)/2.0; //calculating area
        } return allAreas;
    }
    
    //method for writing output file
    public static void writeData(String[] pilotNames, double[] pilotAreas, int allPilots){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("pilot_areas.txt"));
            for (int pilot = 0; pilot < allPilots; pilot++){
                double area = pilotAreas[pilot];
                String output = String.format("%s %.2f", pilotNames[pilot], area); //formatting output line
                writer.write(output);
                writer.newLine();
            }
            writer.close();
            System.out.println("pilot_areas.txt is ready.");
        } catch (IOException e){
            System.err.println("Error writing file");
        }
    }
}