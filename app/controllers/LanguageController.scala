package controllers


import com.google.inject.Inject
import models.LanguageRepo
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
class LanguageController @Inject()(languageRepo: LanguageRepo) extends Controller {


  val updateForm = Form{
    tuple(
      "id"->nonEmptyText,
      "name"->nonEmptyText,
      "fluency"->nonEmptyText
    )
  }

  val langForm = Form{

    tuple(
      "name"  -> nonEmptyText,
      "fluency" ->nonEmptyText
    )
  }

  def show = Action.async{ implicit request =>
//    languageRepo.create()
    languageRepo.getAll.map{a=>Ok(views.html.language(request.session.get("admin"))(a)(langForm)(updateForm))}

  }
  def add = Action.async{ implicit request =>
    val userId = request.session.get("id")

    langForm.bindFromRequest.fold(
      badform => Future{BadRequest("")},
      validform => {
        languageRepo.add(validform._1, validform._2,Integer.parseInt(userId.get)).map{
        a=>Redirect(routes.LanguageController.show)}
      }
    )
  }
  def update   = Action.async{ implicit request =>
    val userId = request.session.get("id")

    updateForm.bindFromRequest.fold(
      badForm =>Future{BadRequest(" ")},
      validForm => {
        languageRepo.update(Integer.parseInt(validForm._1),validForm._2, validForm._3,Integer.parseInt(userId.get)).map{
        a=>Redirect(routes.LanguageController.show)}
      }

    )

  }

  def delete(id:String) = Action{
    languageRepo.delete(Integer.parseInt(id))
    Redirect(routes.LanguageController.show)
  }

}
