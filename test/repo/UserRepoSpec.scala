package repo

import models.{User, UserRepo}
import org.specs2.mutable.Specification
import play.api.Application
import play.api.test.WithApplication

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
  * Created by knoldus on 10/3/16.
  */

class UserRepoSpec extends Specification {

  //sequential

  def userRepo(implicit app: Application) = Application.instanceCache[UserRepo].apply(app)

  "User repository" should {

    "get a record" in new WithApplication {
      val result = userRepo.getUser("rishabh@gmail.com")
      val response = Await.result(result, Duration.Inf)
      response.get.name === "rishabh"
    }
    "get all record" in new WithApplication {
      val result = userRepo.getAll
      val response = Await.result(result, Duration.Inf)
      response.head === User("rishabh","rishabh@gmail.com","1992","35555555",true,1)
    }

  }


}