package repo

import models.LanguageRepo
import org.specs2.mutable.Specification
import play.api.Application
import play.api.test.WithApplication

import scala.concurrent.Await
import scala.concurrent.duration.Duration
/**
  * Created by kunal on 9/3/16.
  */



class LanguageRepoSpec extends Specification{

  sequential
  def langRepo(implicit app: Application) = Application.instanceCache[LanguageRepo].apply(app)

  "User repository" should {

    "get a record" in new WithApplication {
      val result = langRepo.getAll
      val response = Await.result(result,Duration.Inf)
      response.head.name === "hindi"
    }



    "delete a record" in new WithApplication {
      val result = langRepo.delete(2)
      val response = Await.result(result,Duration.Inf)
      response === 1
    }

    "add a record" in new WithApplication {
      val result = langRepo.add("spanish","good",1)
      val response = Await.result(result,Duration.Inf)
      response === 1
    }
    "UPDATE a record" in new WithApplication {
      val result = langRepo.update(2,"spanish","worst",3)
      val response = Await.result(result,Duration.Inf)
      response === 1
    }





  }

}