/**
  * Created by kunal on 11/3/16.
  */

class InternControllerSpec {
  import controllers.InternController
  import models.{User, UserRepo}
  import org.mockito.Mockito._
  import org.specs2.mock.Mockito
  import play.api.test.{FakeRequest, PlaySpecification, WithApplication}
  import play.api.libs.concurrent.Execution.Implicits.defaultContext
  import scala.concurrent.Future

  /**
    * Created by kunal on 11/3/16.
    */

  class InternControllerSpec extends PlaySpecification with Mockito {
    sequential
    "intern Controller " should{

      val service=mock[UserRepo]
      val controller=new InternController(service)

      "show users" in new WithApplication() {

        when(service.getAll).thenReturn(Future(List(User("kunal","k@k.com","kunal","9999",true,1))))

        val res=call(controller.show,FakeRequest(GET,"/showinterns"))

        status(res) must equalTo(200)
      }


    }
  }

}
