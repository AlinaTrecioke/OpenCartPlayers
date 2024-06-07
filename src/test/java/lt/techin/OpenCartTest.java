package lt.techin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class OpenCartTest extends BaseTest {


    ////////Paprastas MP3playlist testas//////////
    @Test
    void testMP3PlayersPage() throws InterruptedException {
        OpenCartPage openCartPage = new OpenCartPage(driver);
        openCartPage.openMP3PlayersPage();
        openCartPage.showItemsAsList();

        List<String> mp3Items = new ArrayList<>();
        mp3Items.add("iPod Classic");
        mp3Items.add("iPod Nano");
        mp3Items.add("iPod Shuffle");
        mp3Items.add("iPod Touch");
        List expected = mp3Items;
        List actual = openCartPage.getItemNames();
        Assertions.assertEquals(expected, actual, "Actual items are not the same as expected items.");

        int expectedListSize = mp3Items.size();
        List<String> itemNames = openCartPage.getItemNames();
        for (String name : itemNames) {
            Assertions.assertEquals(expectedListSize, itemNames.size(), "Actual list size is not the same as the expected list size.");
        }

    }

    ////////Parametrizuotas MP3playlist testas//////////
    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/testdata.csv", numLinesToSkip = 1)
    void testMP3Page(String Item_name_Csv) {
        OpenCartPage openCartPage = new OpenCartPage(driver);
        openCartPage.openMP3PlayersPage();
        openCartPage.showItemsAsList();
        List<String> expected = readCSV("src/test/resources/testdata.csv");
        List actual = openCartPage.getItemNames();
        Assertions.assertEquals(expected, actual);

        int expectedListSize = expected.size();
        List<String> itemNames = openCartPage.getItemNames();
        for (String name : itemNames) {
            Assertions.assertEquals(expectedListSize, itemNames.size());
        }
    }


    //////////Paprastas AddToCart testas//////////
    @Test
    void testAddToCartItem() {
        OpenCartPage openCartPage = new OpenCartPage(driver);
        openCartPage.openMP3PlayersPage();
        openCartPage.showItemsAsList();
        String itemName = openCartPage.getIPodClassicName();
        openCartPage.addItemToTheCard();
        Assertions.assertTrue(openCartPage.successMessageShowsUp());
        String expectedMessage = ("You have added " + itemName + " to your shopping cart!");
        Assertions.assertTrue(openCartPage.getSuccessMessageText().contains(expectedMessage));
    }


    /////////////Sudetingesnis AddToCart testas///////
    @Test
    void addToCart() {
        OpenCartPage openCartPage = new OpenCartPage(driver);
        List<String> csvData = readCSV("src/test/resources/testdata.csv");
        csvData.forEach(System.out::println);
        for (String name: csvData) {
            openCartPage.openMP3PlayersPage();
            openCartPage.showItemsAsList();
            openCartPage.csvAddItemToTheCard(name);
        }

        

    }


    public static List<String> readCSV(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        lines.remove(0);
        return lines;
    }


}