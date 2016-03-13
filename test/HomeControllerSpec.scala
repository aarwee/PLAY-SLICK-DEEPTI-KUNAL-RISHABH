/**
  * Created by kunal on 10/3/16.
  */


import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._
import play.api.test.Helpers._
import play.api.test._

@RunWith(classOf[JUnitRunner])
class HomeControllerSpec extends Specification {
  sequential
  "home Controller" should{

    //    val service=mock[Home]
    //    val controller=new HomeController

    "show login" in new WithApplication() {

      //      when(service.getAll).thenReturn(Future(List(Language("hindi","good",1,1))))

      val res=route(FakeRequest(GET,"/home")).get

      status(res) must equalTo(200)
    }


    "invalid email" in new WithApplication() {

      //      when(service.add("hindi","good",1)).thenReturn(Future(1))

      val res=route(FakeRequest(GET,"/validateUser").withFormUrlEncodedBody("email"->"k","password"->"kunal")).get

      status(res) must equalTo(400)
    }

    "empty password" in new WithApplication() {

      //      when(service.add("hindi","good",1)).thenReturn(Future(1))

      val res=route(FakeRequest(GET,"/validateUser").withFormUrlEncodedBody("email"->"k@k.com","password"->"")).get

      status(res) must equalTo(400)
    }

    "user does not exist" in new WithApplication() {

      //      when(service.add("hindi","good",1)).thenReturn(Future(1))

      val res=route(FakeRequest(GET,"/validateUser").withFormUrlEncodedBody("email"->"k@k.in","password"->"kuna")).get

      status(res) must equalTo(400)
    }


  }
}
