import controllers.AssignmentController
import models.{Assignment, AssignmentRepo}
import org.mockito.Mockito._
import org.specs2.mock.Mockito
import play.api.test.{FakeRequest, PlaySpecification, WithApplication}
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

/**
  * Created by knoldus on 11/3/16.
  */


class AssignmentControllerSpec  extends PlaySpecification with Mockito {
  sequential
  "Assignment Controller " should{



    val service=mock[AssignmentRepo]
    val controller=new AssignmentController(service)

    "show assignment" in new WithApplication() {

      when(service.getAll).thenReturn(Future(List(Assignment("scala","2015-08-08",22,"good",1))))

      val res=call(controller.show,FakeRequest(GET,"/assignment").withSession("admin"->"true","id"->"1"))

      println(res)
      status(res) must equalTo(200)
    }


    "add assignemnt" in new WithApplication() {

      when(service.add("scala","2015-08-08",22,"good",1)).thenReturn(Future(1))

      val res=call(controller.add,FakeRequest(POST,"/addassignment").withFormUrlEncodedBody("name"->"scala","date"->"2015-08-08","marks"->"22","remarks"->"good").withSession("id"->"1"))

      status(res) must equalTo(303)
    }

    "delete assignment" in new WithApplication() {

      when(service.delete(1)).thenReturn(Future(1))

      val res=call(controller.delete("1"),FakeRequest(GET,"/deleteassignment/1").withSession("id"->"1"))

      status(res) must equalTo(303)
    }
    "update assignment" in new WithApplication() {

      when(service.update(1,"scala","2015-08-08",22,"good",1)).thenReturn(Future(1))

      val res=call(controller.update,FakeRequest(POST,"/updateassignment").withFormUrlEncodedBody("name"->"scala","date"->"2015-08-08","marks"->"22","remarks"->"good","id"->"1").withSession("id"->"1"))
      status(res) must equalTo(303)
    }

    "update assignment with invalid date" in new WithApplication() {

      when(service.update(1,"scala","2015",22,"good",1)).thenReturn(Future(1))

      val res=call(controller.update,FakeRequest(POST,"/updateassignment").withFormUrlEncodedBody("name"->"scala","date"->"2015","marks"->"22","remarks"->"good","id"->"1").withSession("id"->"1"))
      status(res) must equalTo(400)
    }

  }

}
