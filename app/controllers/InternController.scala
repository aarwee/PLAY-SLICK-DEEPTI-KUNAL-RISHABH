package controllers

import com.google.inject.Inject
import models._
import play.api.mvc.{Controller, Action}
import play.api.Play.current
import play.api.i18n.Messages.Implicits._
import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * Created by knoldus on 11/3/16.
  */
class InternController@Inject()(userRepo: UserRepo, awardsRepo: AwardsRepo, assignmentRepo: AssignmentRepo, languageRepo: LanguageRepo, programmingRepo: ProgrammingRepo) extends Controller{

  def show = Action{ implicit request =>



      val userList = Await.result(userRepo.getAll,2 second)
      val awardList =  Await.result(awardsRepo.getAll,2 second)
      val assignmentList = Await.result(assignmentRepo.getAll,2 second)
      val languageList = Await.result(languageRepo.getAll,2 second)
      val programminList = Await.result(programmingRepo.getAll,2 second)

      Ok(views.html.intern(userList)(awardList)(assignmentList)(languageList)(programminList))
  }
}
