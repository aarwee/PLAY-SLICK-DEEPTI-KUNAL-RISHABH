import controllers.LanguageController
import models.{Language, LanguageRepo}
import org.junit.runner._
import org.mockito.Mockito._
import org.specs2.mock.Mockito
import org.specs2.runner._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.test._

import scala.concurrent.Future

@RunWith(classOf[JUnitRunner])
class LanguageControllerSpec extends PlaySpecification with Mockito {
  sequential
  "language Controller " should{

    val service=mock[LanguageRepo]
    val controller=new LanguageController(service)

    "show languages" in new WithApplication() {

      when(service.getAll).thenReturn(Future(List(Language("hindi","good",1,1))))
      val res=call(controller.show,FakeRequest(GET,"/language").withSession("admin"->"true","id"->"1"))
      status(res) must equalTo(200)

    }



    "add languages" in new WithApplication() {

      when(service.add("hindi","good",1)).thenReturn(Future(1))

      val res=call(controller.add,FakeRequest(POST,"/addlanguage").withFormUrlEncodedBody("name"->"hindi","fluency"->"good").withSession("id"->"1"))

      status(res) must equalTo(303)
    }

    "delete languages" in new WithApplication() {

      when(service.delete(1)).thenReturn(Future(1))

      val res=call(controller.delete("1"),FakeRequest(GET,"/deletelanguage/1").withSession("id"->"1"))

      status(res) must equalTo(303)
    }
    "update languages" in new WithApplication() {

      when(service.update(1,"java","good",1)).thenReturn(Future(1))

      val res=call(controller.update,FakeRequest(POST,"/updatelanguage").withFormUrlEncodedBody("id"->"1","name"->"java","fluency"->"good").withSession("id"->"1"))
      status(res) must equalTo(303)
    }

    "get Json object" in new WithApplication() {

      when(service.getById(3)).thenReturn(Future(Option(Language("hindi", "good",1))))
      val res = call(controller.getJson(3), FakeRequest(GET, "/getlanguage/:3").withSession("id" -> "2"))
      status(res) must equalTo(200)

    }

    "show languages" in new WithApplication() {

      when(service.getAll).thenReturn(Future(List(Language("hindi","good",1,1))))
      val res=call(controller.show,FakeRequest(GET,"/language"))
      status(res) must equalTo(303)

    }
    "get language" in new WithApplication() {
      when(service.getByUserId(1)).thenReturn(Future(List(Language("english", "very good",1))))
      val res = call(controller.getLanguage, FakeRequest(GET, "/getlanguages").withSession("id" -> "1", "admin" -> "true"))
      status(res) must equalTo(200)
    }
    "get language by id" in new WithApplication() {
      when(service.getByUserId(2)).thenReturn(Future(List(Language("gujarati", "good",2))))
      val res = call(controller.getById(2), FakeRequest(GET, "/showlanguage/2").withSession("id" -> "2"))
      status(res) must equalTo(200)
    }


  }
}