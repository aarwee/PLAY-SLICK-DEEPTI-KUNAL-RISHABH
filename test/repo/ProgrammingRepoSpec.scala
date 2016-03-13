package repo

import models.ProgrammingRepo
import org.specs2.mutable.Specification
import play.api.Application
import play.api.test.WithApplication

import scala.concurrent.Await
import scala.concurrent.duration.Duration


/**
  * Created by knoldus on 10/3/16.
  */

class ProgrammingRepoSpec extends Specification {
  sequential
  def progRepo(implicit app: Application) = Application.instanceCache[ProgrammingRepo].apply(app)

  "User repository" should {

    "get a record" in new WithApplication {
      val result = progRepo.getAll
      val response = Await.result(result, Duration.Inf)
      response.head.name === "groovy"
    }

    "delete a record" in new WithApplication {
      val result = progRepo.delete(2)
      val response = Await.result(result,Duration.Inf)
      response === 1
    }

    "add a record" in new WithApplication {
      val result = progRepo.add("java","good",1)
      val response = Await.result(result,Duration.Inf)
      response === 1
    }
    "UPDATE a record" in new WithApplication {
      val result = progRepo.update(2,"c++","worst",3)
      val response = Await.result(result,Duration.Inf)
      response === 1
    }
    "get a record by id" in new WithApplication {
      val result = progRepo.getById(1)
      val response = Await.result(result,Duration.Inf)
      response.get.name === "groovy"
    }
    "get a record by id" in new WithApplication {
      val result = progRepo.getByUserId(4)
      val response = Await.result(result,Duration.Inf)
      response.head.name === "scala"
    }

    "delete a record" in new WithApplication {
      val result = progRepo.delete(5)
      val response = Await.result(result,Duration.Inf)
      response === 0
    }
    "get a record by user id" in new WithApplication {
      val result = progRepo.getById(9)
      val response = Await.result(result,Duration.Inf)
      response === None
    }


  }



}
