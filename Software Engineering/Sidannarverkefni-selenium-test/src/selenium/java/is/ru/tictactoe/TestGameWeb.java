package is.ru.tictactoe;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TestGameWeb extends SeleniumTestWrapper {
  @Test
  public void testTitleMatches() {
    driver.get(baseUrl);
    assertEquals("TicTacToe", driver.getTitle());
  }

  @Test
  public void testSimpleAdd() throws Exception {
  	//driver.get(baseUrl);
    /* Remove Thread.sleep... */
    //Thread.sleep(2000);
    //WebElement input = driver.findElement(By.id("number"));
    //input.sendKeys("1");
    /* Remove Thread.sleep... */
    //Thread.sleep(2000);
    /* ... finish test! */

  }
}