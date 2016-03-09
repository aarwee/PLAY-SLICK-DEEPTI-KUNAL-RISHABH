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
import play.api.libs.concurrent.Execution.Implicits._

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


  val updateForm= Form(
    tuple(
      "id"-> nonEmptyText,
      "name"-> nonEmptyText,
      "description"-> nonEmptyText,
      "year" -> nonEmptyText
    )
  )

  def show = Action.async{ implicit request =>
    awardsRepo.getAll.map{
      a=> Ok(views.html.awards("")(a)(awardForm)(updateForm))
    }
  }

  def add = Action{ implicit request =>
    val userId = request.session.get("id")

    awardForm.bindFromRequest.fold(
      badform => BadRequest(""),
      validform => {
        awardsRepo.add(validform._1, validform._2, validform._3, Integer.parseInt(userId.get))
        Redirect(routes.AwardsController.show)
      }
    )
  }
def update   = Action{ implicit request =>
  val userId = request.session.get("id")

  updateForm.bindFromRequest.fold(
    baddFOrm =>BadRequest(" "),
    validForm => { awardsRepo.update(Integer.parseInt(validForm._1),validForm._2, validForm._3,validForm._4,Integer.parseInt(userId.get))
      Redirect(routes.AwardsController.show)
    }

  )

}

  def delete(id:String) = Action{
    awardsRepo.delete(Integer.parseInt(id))
    Redirect(routes.AwardsController.show)
  }
}
