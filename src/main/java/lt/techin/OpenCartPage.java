package lt.techin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OpenCartPage extends BasePage {


    @FindBy(css = "li:nth-of-type(8) > .dropdown-toggle.nav-link")
    private WebElement selectionMP3Players;

    @FindBy(css = "li:nth-of-type(8) > .dropdown-menu > .see-all")
    private WebElement showAllMP3Players;

    @FindBy(css = "button#button-list > .fa-solid.fa-table-list")
    private WebElement buttonList;


    @FindBy(css = "div > .product-thumb h4 > a")
    List<WebElement> items;


    @FindBy(css = "div > .product-thumb h4 > a")
    private WebElement item;

    @FindBy(css = "#product-list .content [href='http\\:\\/\\/192\\.168\\.88\\.225\\/en-gb\\/product\\/mp3-players\\/ipod-classic']")
    private WebElement iPodClassic;

    @FindBy(css = "input#input-quantity")
    private WebElement inputQty;

    @FindBy(css = "div#product-info > ul > li:nth-of-type(2) > a")
    private WebElement backToMP3;

    @FindBy(css = "button#button-cart")
    private WebElement addToCart;


    @FindBy (css = "div#alert")
    private WebElement successMessage;



    public OpenCartPage(WebDriver driver) {
        super(driver);
    }


    public void openMP3PlayersPage() {
        selectionMP3Players.click();
        showAllMP3Players.click();
    }


    public void showItemsAsList() {
        buttonList.click();
    }







    public List<String> getItemNames() {
        List<String> itemNames = new ArrayList<>();
        for (WebElement item : items) {
            itemNames.add(item.getText());
        }
        return itemNames;
    }

//
//    public void addItemsToTheCard(int quantity) {
//
//        List<String> itemNames = new ArrayList<>();
//        for (WebElement item : items) {
//           item.click();
//            inputQty.clear();
//            inputQty.sendKeys(String.valueOf(quantity));
//            addToCart.click();
//        }
//        }
//
//    }

    public void csvAddItemToTheCard(String Item_name)  {

        WebElement itemAsCSV = driver.findElement(By.linkText(Item_name));
        new Actions(driver).scrollToElement(itemAsCSV).perform();
        Random random = new Random();
        int randomQuantity = random.nextInt(20) + 1;
        itemAsCSV.click();
        inputQty.clear();
        inputQty.sendKeys(String.valueOf(randomQuantity));
        addToCart.click();
    }






    public void addItemToTheCard()  {
        Random random = new Random();
        int randomQuantity = random.nextInt(20) + 1;
        iPodClassic.click();
        inputQty.clear();
        inputQty.sendKeys(String.valueOf(randomQuantity));
        addToCart.click();
    }


    public boolean successMessageShowsUp(){
//        Wait <WebDriver> wait = new FluentWait<>(driver)
//                .withTimeout(Duration.ofSeconds(3));
//        wait.until(d -> (successMessage.isDisplayed()));
        return successMessage.isDisplayed();
    }

    public String getIPodClassicName(){
        return iPodClassic.getText();
    }

    public String getSuccessMessageText(){
        return successMessage.getText();
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


}


//    public List <String> itemNamesList(){
//        List<String> itemNames = new ArrayList<>();
//        for (WebElement item : items) {
//            itemNames.add(item.getText());}
//        return itemNames;
//    }



