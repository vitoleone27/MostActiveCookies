import java.io.File;
import java.io.*;
import java.util.*;
import java.util.ArrayList;


class CookieParser {
    public File file;
    ArrayList<String> cookies;
    String date;

    /**
     * Instantiates a cookieParser object
     * @param file input File file to be acessed throughout program
     * @param date input String date to be acessed throughout program
     */
    public CookieParser(File file, String date){
        this.file = file;
        this.date = date;
    }

    /**
     * Reads through every line of the log. Iterations split line into the cookie ID and timestamp. If cookie date
     * is equal to constructor date, cookie is added to HashMap cookieMap, with value 1 greater than last occurance.
     * On first appearance, value is 1. Int max updated if any value is greater than current max. Once all lines read,
     * addToCookieList(cookieMap, max) and printCookies() are called.
     * @throws IOException Throws exception if file is invalid or cannot be found
     */
    public void getActiveCookies() throws IOException {
        cookies = new ArrayList<>();
        HashMap<String, Integer> cookieMap = new HashMap<>();
        int max = -1;
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        while ((line = br.readLine()) != null)
        {
            String[] cookieDetails = line.split(",");
            String[] cookieDate = cookieDetails[1].split("T");
            if(date.equals(cookieDate[0])) {
                if (cookieMap.containsKey(cookieDetails[0])) {
                    cookieMap.put(cookieDetails[0], cookieMap.get(cookieDetails[0]) + 1);
                }
                else {
                    cookieMap.put(cookieDetails[0], 1);
                }
                if (cookieMap.get(cookieDetails[0])>max)
                    max = cookieMap.get(cookieDetails[0]);
            }
        }
        addToCookieList(cookieMap, max);

        printCookies();
    }

    /**
     * Takes all entries in a Hashmap cookieCount and if their value is equal to max, then entry is added
     * to an ArrayList
     * @param cookieMap HashMap containing key for the cookie ID, and value of how many times
     *                    the key appeared in the log on the specified date
     * @param max Int representing the greatest number of times a cookie appeared on a date
     */
    private void addToCookieList(HashMap<String, Integer> cookieMap, int max) {
        for (Map.Entry<String,Integer> mapElement : cookieMap.entrySet()) {
            if (mapElement.getValue() == max)
                cookies.add(mapElement.getKey());
        }
    }

    /**
     * Cycles through all of the active cookies added to the array list and prints them on a new line
     */
    public void printCookies(){
        for(String cookie: cookies){
            System.out.println(cookie);
        }
    }

    /**
     *
     * @return returns size of the list of active cookies
     */
    public int getNumActiveCookies(){
        return cookies.size();
    }

    /**
     * Runs the program that prints the cookies in the terminal console
     * @param args args[0] is the file, and args[1] is the date
     * @throws IOException throws exception if file is not found
     */
    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        String date = args[1];
        CookieParser cookieParser = new CookieParser(file, date);
        cookieParser.getActiveCookies();
    }
}

