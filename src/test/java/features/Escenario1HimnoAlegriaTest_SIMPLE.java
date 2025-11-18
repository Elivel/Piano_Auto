package features;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Escenario1HimnoAlegriaTest_SIMPLE {

    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void debeTocarEscenario1() throws InterruptedException {
        driver.get("https://www.musicca.com/es/piano");
        Thread.sleep(2000);
        
        // Tocar secuencia: SI SI DO RE RE DO SI LA SOL SOL LA SI SI LA LA
        String[] notas = {"[data-note='2b']", "[data-note='2b']", "[data-note='1c']", 
                          "[data-note='3d']", "[data-note='3d']", "[data-note='1c']",
                          "[data-note='2b']", "[data-note='2a']", "[data-note='2g']",
                          "[data-note='2g']", "[data-note='2a']", "[data-note='2b']",
                          "[data-note='2b']", "[data-note='2a']", "[data-note='2a']"};
        
        for (String selector : notas) {
            driver.findElement(By.cssSelector(selector)).click();
            Thread.sleep(300);
        }
        
        System.out.println("✓ Escenario 1 completado: Himno de la Alegría");
    }
}
