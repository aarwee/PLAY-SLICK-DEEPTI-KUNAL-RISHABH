package controllers

import com.google.inject.Inject
import models.{ProgrammingRepo, Programming}
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
class ProgrammingController @Inject()(programmingrepo: ProgrammingRepo) extends Controller {


  val progForm = Form{

    tuple(
      "name"  -> nonEmptyText,
      "fluency" ->nonEmptyText
    )
  }

  val updateForm = Form(
    tuple(
      "id" -> nonEmptyText,
      "name" -> nonEmptyText,
      "fluency" -> nonEmptyText

    )
  )


  def show = Action.async { implicit request =>
//    programmingrepo.create()
    programmingrepo.getAll.map {
      list => Ok(views.html.programming("")(list)(progForm)(updateForm))
    }
  }
  def add = Action{ implicit request =>
    val userId = request.session.get("id")

    progForm.bindFromRequest.fold(
      badform => BadRequest(""),
      validform => {
        programmingrepo.add(validform._1, validform._2,Integer.parseInt(userId.get))
        Redirect(routes.ProgrammingController.show)
      }
    )
  }
  def update   = Action{ implicit request =>
    val userId = request.session.get("id")

    updateForm.bindFromRequest.fold(
      baddFOrm =>BadRequest(" "),
      validForm => { programmingrepo.update(Integer.parseInt(validForm._1),validForm._2, validForm._3,Integer.parseInt(userId.get))
        Redirect(routes.ProgrammingController.show)
      }

    )

  }

  def delete(id:String) = Action{
    programmingrepo.delete(Integer.parseInt(id))
    Redirect(routes.ProgrammingController.show)
  }


}

