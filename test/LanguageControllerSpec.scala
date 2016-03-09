import models.LanguageRepo
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.Application
import play.api.test.WithApplication

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
  * Created by kunal on 9/3/16.
  */


@RunWith(classOf[JUnitRunner])
class LanguageControllerSpec extends Specification{

  def langRepo(implicit app: Application) = Application.instanceCache[LanguageRepo].apply(app)
  "User repository" should {

    "get a record" in new WithApplication {
      val result = langRepo.getAll
      val response = Await.result(result,Duration.Inf)
      response.head.name === "hindi"
    }



  }

}