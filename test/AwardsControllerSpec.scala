
import controllers.AwardsController
import models.{Awards, AwardsRepo}
import org.junit.runner._
import org.mockito.Mockito._
import org.specs2.mock.Mockito
import org.specs2.runner._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.test._

import scala.concurrent.Future

/**
  * Created by kunal on 11/3/16.
  */
@RunWith(classOf[JUnitRunner])
class AwardsControllerSpec extends PlaySpecification with Mockito {
  sequential
  "awards Controller " should{

    val service=mock[AwardsRepo]
    val controller=new AwardsController(service)

    "show Awards" in new WithApplication() {

      when(service.getAll).thenReturn(Future(List(Awards("scjp","good","1992",1,1))))

      val res=call(controller.show,FakeRequest(GET,"/awards").withSession("admin"->"true"))

      println(res)
      status(res) must equalTo(200)
    }



    "add award" in new WithApplication() {

      when(service.add("micro","good","1992",1)).thenReturn(Future(1))

      val res=call(controller.add,FakeRequest(POST,"/add").withFormUrlEncodedBody("name"->"micro","description"->"good","year"->"1992","userId"->"1").withSession("id"->"1"))

      status(res) must equalTo(303)
    }

    "delete Awards" in new WithApplication() {

      when(service.delete(1)).thenReturn(Future(1))

      val res=call(controller.delete("1"),FakeRequest(GET,"/deleteawards/1").withSession("id"->"1"))

      status(res) must equalTo(303)
    }
    "update languages" in new WithApplication() {

      when(service.update(1,"scjp","good","2000",2)).thenReturn(Future(1))

      val res=call(controller.update,FakeRequest(POST,"/update").withFormUrlEncodedBody("id"->"1","name"->"scjp","description"->"good","year"->"2000","userId"->"2").withSession("id"->"2"))
      status(res) must equalTo(303)
    }

  }}