import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class QuantcastTests {

    /**
     * Tests if program's list is of proper size for a specific date
     * @throws IOException Throws exception if file not found or cannot be accessed
     */
    @Test
    public void testCookieParserListSize() throws IOException {
        File file = new File("tests/logFile.csv");
        CookieParser cookieParser = new CookieParser(file,"2018-12-08");
        cookieParser.getActiveCookies();
        assertEquals(3,cookieParser.getNumActiveCookies());
    }


    /**
     * Tests if program's list contains expected cookie ID value for a specific date
     * @throws IOException Throws exception if file not found or cannot be accessed
     */
    @Test
    public void testCookieParserListCookies() throws IOException {
        File file = new File("tests/logFile.csv");
        CookieParser cookieParser = new CookieParser(file,"2018-12-09");
        cookieParser.getActiveCookies();
        ArrayList<String> cookies = cookieParser.cookies;
        assertTrue(cookies.contains("AtY0laUfhglK3lC7"));
    }

    /**
     * Tests if program's list will not add anything if file is empty
     * @throws IOException Throws exception if file not found or cannot be accessed
     */
    @Test
    public void testCookieParserEmptyFile() throws IOException {
        File file = new File("tests/empty.csv");
        CookieParser cookieParser = new CookieParser(file, "2018-12-07");
        cookieParser.getActiveCookies();
        assertEquals(0, cookieParser.getNumActiveCookies());
    }

    /**
     * Tests if program's list will not add anything if file does not contain proper cookies and time stamps
     * @throws IOException Throws exception if file not found or cannot be accessed
     */
    @Test
    public void testCookieParserNoCookieFile() throws IOException {
        File file = new File("tests/noCookies.csv");
        CookieParser cookieParser = new CookieParser(file,"2018-12-07");
        cookieParser.getActiveCookies();
        assertEquals(0, cookieParser.getNumActiveCookies());
    }

}
