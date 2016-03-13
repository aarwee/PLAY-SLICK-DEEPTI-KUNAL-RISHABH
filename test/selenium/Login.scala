package selenium

import java.util.concurrent.TimeUnit

import org.openqa.selenium.firefox.FirefoxDriver
import org.scalatest.FlatSpec

/**
  * Created by kunal on 11/3/16.
  */
class Login extends FlatSpec{

  val baseUrl ="http://localhost:9000/home"

  "User" should "successfully hit the Url" in {
    val driver = new FirefoxDriver()
    driver.get(baseUrl)
    driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS)
    driver.findElementById("email").sendKeys("rishabh@gmail.com")
    driver.findElementById("password").sendKeys("199992")
    driver.findElementByClassName("btn-default").click()
    driver.findElementByCssSelector("BODY").getText.contains("invalid")
    driver.findElementById("email").sendKeys("rishabh@gmail.com")
    driver.findElementById("password").sendKeys("1992")
    driver.findElementByClassName("btn-default").click()
    driver.findElementByCssSelector("BODY").getText.contains("AWARDS")
    driver.findElementById("awards").click()
    driver.findElementByCssSelector("BODY").getText.contains("PROGRAMMING")
    driver.findElementById("prog").click()
    driver.findElementByCssSelector("BODY").getText.contains("LANGUAGE")
    driver.findElementById("lang").click()
    driver.findElementByCssSelector("BODY").getText.contains("ASSIGNMENT")
    driver.findElementById("assign").click()
  Thread.sleep(2000)
  }

}
