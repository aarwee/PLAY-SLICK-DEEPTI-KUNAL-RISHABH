package controllers

import java.lang.ProcessBuilder.Redirect

import com.google.inject.Inject
import models.{AwardsRepo, User}
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.iteratee.{Iteratee, Enumerator}
import play.api.mvc._
import play.api.Play.current
import play.api.i18n.Messages.Implicits._
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

/**
  * Created by knoldus on 8/3/16.
  */
class AwardsController@Inject()(awardsRepo: AwardsRepo) extends Controller{

  val awardForm= Form(
    tuple(
      "name"-> nonEmptyText,
      "description"-> nonEmptyText,
      "year" -> nonEmptyText
    )
  )

  def show = Action { implicit request =>
//    awardsRepo.create()
    val result = Await.result(awardsRepo.getAll , 2 second)
    Ok(views.html.awards("")(result)(awardForm))
  }

  def add = Action{ implicit request =>
    val userId = request.session.get("id")
//    val result = Await.result(awardsRepo.getAll , 2 second)
//    Ok(views.html.awards("")(result)(awardForm))
    awardForm.bindFromRequest.fold(
      badform => BadRequest(""),
      validform => {
        awardsRepo.add(validform._1, validform._2, validform._3, Integer.parseInt(userId.get))
        Redirect(routes.AwardsController.show)
      }
    )
  }
}
