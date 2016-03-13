//package selenium
//
//import java.util.concurrent.TimeUnit
//
//import org.openqa.selenium.firefox.FirefoxDriver
//import org.scalatest.FlatSpec
//
///**
//  * Created by kunal on 11/3/16.
//  */
//class Login extends FlatSpec{
//
//  val baseUrl ="http://localhost:9000/home"
//
//  "User" should "successfully hit the Url" in {
//    val driver = new FirefoxDriver()
//    driver.get(baseUrl)
//    driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS)
//    driver.findElementById("email").sendKeys("k@k.com")
//    driver.findElementById("password").sendKeys("kunal")
//    driver.findElementByClassName("btn-default").click()
//    driver.findElementByCssSelector("BODY").getText.contains("Invalid")
//    driver.findElementById("awards").click()
//    driver.findElementByCssSelector("BODY").getText.contains("awards")
//    driver.findElementByClassName("btn-info").click()
//    driver.findElementByCssSelector(".modal-open #awardModal .modal-dialog .modal-content .modal-header .modal-body #awardForm #name_field #name").sendKeys("ooo")
//    driver.findElementByCssSelector("#awardModal .modal-dialog .modal-content .modal-header .modal-body #awardForm #name_field #description").sendKeys("ooo")
//    driver.findElementByCssSelector("#awardModal .modal-dialog .modal-content .modal-header .modal-body #awardForm #name_field #year").sendKeys("ooo")
//    driver.findElementByCssSelector("#awardModal .modal-dialog .modal-content .modal-header .modal-body #awardForm #name_field #addaward").click()
//    driver.findElementByCssSelector("BODY").getText.contains("ooo")
//    Thread.sleep(4000)
//  }
//
//}
