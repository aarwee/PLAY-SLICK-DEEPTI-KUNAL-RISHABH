import controllers.ProgrammingController
import models.{Programming, ProgrammingRepo}
import org.mockito.Mockito._
import org.specs2.mock.Mockito
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.test._

import scala.concurrent.Future

/**
  * Created by knoldus on 10/3/16.
  */
class ProgrammingControllerSpec extends PlaySpecification with Mockito {
  sequential
  "planguage Controller " should{

    val service=mock[ProgrammingRepo]
    val controller=new ProgrammingController(service)

    "show planguages" in new WithApplication() {

      when(service.getAll).thenReturn(Future(List(Programming("java","good",1,1))))
      val res=call(controller.show,FakeRequest(GET,"/programming").withSession("admin"->"true","id"->"1"))
      println(res)
      status(res) must equalTo(200)
    }



    "add planguages" in new WithApplication() {

      when(service.add("java","good",1)).thenReturn(Future(1))
      val res=call(controller.add,FakeRequest(POST,"/addprogramming").withFormUrlEncodedBody("name"->"java","fluency"->"good").withSession("id"->"1"))
      status(res) must equalTo(303)
    }

    "delete planguages" in new WithApplication() {

      when(service.delete(1)).thenReturn(Future(1))
      val res=call(controller.delete("1"),FakeRequest(GET,"/deleteprogramming/1").withSession("id"->"1"))
      status(res) must equalTo(303)
    }
    "update planguages" in new WithApplication() {

      when(service.update(1,"java","good",1)).thenReturn(Future(1))
      val res=call(controller.update,FakeRequest(POST,"/updateprogramming").withFormUrlEncodedBody("id"->"1","name"->"java","fluency"->"good").withSession("id"->"1"))
      status(res) must equalTo(303)
    }
    "get Json object" in new WithApplication() {

      when(service.getById(1)).thenReturn(Future(Option(Programming("java", "good",2))))
      val res = call(controller.getJson(1), FakeRequest(GET, "/getassignment/1").withSession("id" -> "1"))
      status(res) must equalTo(200)

    }

    "get programming language" in new WithApplication() {
      when(service.getByUserId(1)).thenReturn(Future(List(Programming("scala", "very good",1))))
      val res = call(controller.getProgramming, FakeRequest(GET, "/getprogramminglanguages").withSession("id" -> "1", "admin" -> "true"))
      status(res) must equalTo(200)
    }

    "get language by id" in new WithApplication() {
      when(service.getByUserId(2)).thenReturn(Future(List(Programming("c++", "good",2))))
      val res = call(controller.getById(2), FakeRequest(GET, "/showprogramming/:id").withSession("id" -> "2"))
      status(res) must equalTo(200)
    }
    "show planguages" in new WithApplication() {

      when(service.getAll).thenReturn(Future(List(Programming("java","good",1,1))))
      val res=call(controller.show,FakeRequest(GET,"/programming"))
      println(res)
      status(res) must equalTo(303)
    }
    "update planguages bad form" in new WithApplication() {

      when(service.update(1,"java","good",1)).thenReturn(Future(1))
      val res=call(controller.update,FakeRequest(POST,"/updateprogramming").withFormUrlEncodedBody("id"->"1","name"->"","fluency"->"").withSession("id"->"1"))
      status(res) must equalTo(400)
    }

  }

}
