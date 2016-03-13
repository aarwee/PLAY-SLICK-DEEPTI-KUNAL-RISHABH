package repo

import models.AssignmentRepo
import org.specs2.mutable.Specification
import play.api.Application
import play.api.test.WithApplication

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
  * Created by knoldus on 10/3/16.
  */

class AssignmentRepoSpec extends Specification {

  sequential
  def assignmentRepo(implicit app: Application) = Application.instanceCache[AssignmentRepo].apply(app)

 "Awards repository" should {

   "get a record" in new WithApplication {
     val result = assignmentRepo.getAll
     val response = Await.result(result, Duration.Inf)
     response.head.name === "c++"
   }
   "delete a record" in new WithApplication {
     val result = assignmentRepo.delete(1)
     val response = Await.result(result, Duration.Inf)
     response === 1
   }
    "add a record" in new WithApplication {
         val result = assignmentRepo.add("scala", "2015-08-08", 22, "good",3)
         val response = Await.result(result, Duration.Inf)
         response === 1

 }
      "UPDATE a record" in new WithApplication {
       val result = assignmentRepo.update(1, "scala", "2015-08-08", 22, "good", 1)
       val response = Await.result(result, Duration.Inf)
     }
 "get record by id" in new WithApplication{
    val result = assignmentRepo.getById(1)
    val response = Await.result(result, Duration.Inf)
   response.get.name === "c++"
    }
    "get record by id" in new WithApplication{
    val result = assignmentRepo.getByUserId(1)
    val response = Await.result(result, Duration.Inf)

   response.head.name === "c++"
 }
  }
}
