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
        List<String> expected = openCartPage.readCSV("src/test/resources/testdata.csv");
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


    /////////////Parametrizuotas AddToCart testas///////
    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/testdata.csv", numLinesToSkip = 1)
    void testAddToCartItem(String Item_name_Csv) {

        OpenCartPage openCartPage = new OpenCartPage(driver);
        openCartPage.openMP3PlayersPage();
        openCartPage.showItemsAsList();
        openCartPage.csvAddItemToTheCard(Item_name_Csv);



    }

    @Test
    void listCsV() {
        OpenCartPage openCartPage = new OpenCartPage(driver);

        List<String> csvData = openCartPage.readCSV("src/test/resources/testdata.csv");

        csvData.forEach(System.out::println);
        for (String name: csvData) {
            openCartPage.openMP3PlayersPage();
            openCartPage.showItemsAsList();
            openCartPage.csvAddItemToTheCard(name);
        }


    }
}

//    public static List<String> readCSV(String filePath) {
//        List<String> lines = new ArrayList<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                lines.add(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        lines.remove(0);
//        return lines;
//    }




//        String iPodClassic = "iPod Classic";
//        String iPodNano = "iPod Nano";
//        String iPodShuffle = "iPod Shuffle";
//        String iPodTouch = "iPod Touch";
//        Assertions.assertEquals(iPodClassic, openCartPage.getItemNameClassic(), "Item name does not match");
//        Assertions.assertEquals(iPodNano, openCartPage.getItemNameNano(), "Item name does not match");
//        Assertions.assertEquals(iPodShuffle, openCartPage.getItemNameShuffle(), "Item name does not match");
//        Assertions.assertEquals(iPodTouch, openCartPage.getItemNameTouch(), "Item name does not match");


//    System.out.println(openCartPage.itemNamesList());


//    @ParameterizedTest
//    @CsvFileSource(files = "src/test/resources/logins.csv", numLinesToSkip = 1)
//    void logins(String username, String password) {
//        driver.findElement(By.id("user-name")).sendKeys(username);
//        driver.findElement(By.id("password")).sendKeys(password);
//        driver.findElement(By.id("login-button")).click();
//
//        String expected = "https://www.saucedemo.com/inventory.html";
//        String actual = driver.getCurrentUrl();
//        Assertions.assertEquals(expected, actual);
//
//    }
//
//
//    @ParameterizedTest
//    @CsvFileSource(files = "src/test/resources/logins.csv", numLinesToSkip = 1)
//    void timeOut(String username, String password) {
//        Assertions.assertTimeout(Duration.ofSeconds(2), () -> {
//            driver.findElement(By.id("user-name")).sendKeys(username);
//            driver.findElement(By.id("password")).sendKeys(password);
//            driver.findElement(By.id("login-button")).click();
//
//            String expected = "https://www.saucedemo.com/inventory.html";
//            String actual = driver.getCurrentUrl();
//            Assertions.assertEquals(expected, actual);
//        });


