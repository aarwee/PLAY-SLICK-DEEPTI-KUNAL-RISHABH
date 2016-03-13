
import org.junit.runner._
import org.specs2.mock.Mockito
import org.specs2.runner._
import play.api.test._

/**
  * Created by kunal on 11/3/16.
  */
@RunWith(classOf[JUnitRunner])
class DashboardControllerSpec extends PlaySpecification with Mockito {
  sequential
  "awards Controller " should{

    "show dashboard" in new WithApplication() {


      val res=route(FakeRequest(GET,"/show").withSession("admin"->"true","id"->"1")).get

      status(res) must equalTo(200)
    }
    "show dashboard without session" in new WithApplication() {


      val res=route(FakeRequest(GET,"/show")).get

      status(res) must equalTo(303)
    }
  }
}