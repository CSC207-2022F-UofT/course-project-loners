package Use_Case;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LocationConverter {

    private static Scanner loadFile(){
        try{
            File file = new File("location_converter.csv");
            Scanner scanner = new Scanner(file);
            return scanner;

        } catch (FileNotFoundException e){
            System.out.println("didn't do it");
            return null;
        }
    }

    // NOTE: THIS METHOD IS SENSITIVE TO SPACES AND CAPITALIZATION IN THE POSTAL CODES, IT WILL RETURN
    // AN EXCEPTION IF THE CODE IS NOT PROPERLY FORMATTED
    public static double[] codeToCoords(String code){
        // Converts a postal code to an array of doubles that represent that location's corresponding
        // coordinates.
        Scanner scanner = loadFile();
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
        if (!scanner.hasNext()){
            throw new NoSuchElementException();
        }
        return null;
    }

    public static String coordsToCode(double[] coords){
        // Converts coordinates to a postal code
        String latitude = Double.toString(coords[0]);
        String longitude = Double.toString(coords[1]);

        Scanner scanner = loadFile();
        scanner.useDelimiter(",");
        scanner.nextLine();
        // skips the title line

        while (scanner.hasNext()){
            String curr = scanner.nextLine();
            String[] currArray = curr.split(",");
            if (curr.contains(latitude) && curr.contains(longitude)){
                String code = currArray[0].substring(1, currArray[0].length()-1);
                return code;

            }
        }
        if (!scanner.hasNext()){
            throw new NoSuchElementException();
        }
        return null;
    }

}
