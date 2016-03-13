package repo

import models.AwardsRepo
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.Application
import play.api.test.WithApplication

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
  * Created by knoldus on 10/3/16.
  */
@RunWith(classOf[JUnitRunner])
class AwardsRepoSpec extends Specification {
  sequential

  def awardsRepo(implicit app: Application) = Application.instanceCache[AwardsRepo].apply(app)

  "Awards repository" should {

    "get a record" in new WithApplication {
      val result = awardsRepo.getAll
      val response = Await.result(result, Duration.Inf)
      response.head.name === "microsoft"
    }

    "delete a record" in new WithApplication {
      val result = awardsRepo.delete(2)
      val response = Await.result(result, Duration.Inf)
      response === 1
    }

    "add a record" in new WithApplication {
      val result = awardsRepo.add("scjp", "good", "2015", 1)
      val response = Await.result(result, Duration.Inf)
      response === 1
    }
    "UPDATE a record" in new WithApplication {
      val result = awardsRepo.update(2, "NCR Certificate", "worst", "2016", 3)
      val response = Await.result(result, Duration.Inf)
      response === 1
    }
    "get a record by id" in new WithApplication {
      val result = awardsRepo.getById(2)
      val response = Await.result(result, Duration.Inf)
      response.get.name === "sun certificate"
    }
    "get a record by User id" in new WithApplication {
      val result = awardsRepo.getByUserId(1)
      val response = Await.result(result, Duration.Inf)
      response.head.name === "microsoft"
    }
  }
}