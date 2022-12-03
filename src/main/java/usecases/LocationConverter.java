package usecases;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * LocationConverter class converts postal code to coordinates
 */
public class LocationConverter {


    /**
     * A private method to load in the file we want to use to do location conversions
     * @return A scanner of the file we want to do the conversion with
     */
    private static Scanner loadFile(){
        try{
            File file = new File("location_converter.csv");
            return new Scanner(file);

        } catch (FileNotFoundException e){
            System.out.println("didn't do it");
            return null;
        }
    }

    /**
     * @param code the postal code, formatted as three characters and a space, followed by three more characters.
     *             All letters are capital.
     * @return The double that is represented by the postal code based on the file
     */
    // NOTE: THIS METHOD IS SENSITIVE TO SPACES AND CAPITALIZATION IN THE POSTAL CODES, IT WILL RETURN
    // AN EXCEPTION IF THE CODE IS NOT PROPERLY FORMATTED
    // PRECONDITION: Postal code is valid and it exists
    public static double[] codeToCoords(String code){
        // Converts a postal code to an array of doubles that represent that location's corresponding
        // coordinates.
        Scanner scanner = loadFile();
        assert scanner != null;
        scanner.useDelimiter(",");
        scanner.nextLine();
        // skips the title line

        // while there are more lines to the file and we didn't find it yet
        while (scanner.hasNext()){
            String curr = scanner.nextLine();
            String[] currArray = curr.split(",");
            if (curr.contains(code)){
                double latitude = Double.parseDouble(currArray[4].substring(1, currArray[4].length()-1));
                double longitude = Double.parseDouble(currArray[5].substring(1, currArray[5].length()-1));

                return new double[]{latitude, longitude};
            }
        }
        // Postal code is not in the file! it does not exist.
        throw new NoSuchElementException();
    }

}
