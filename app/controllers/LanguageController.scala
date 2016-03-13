package controllers


import com.google.inject.Inject
import models.LanguageRepo
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.iteratee.{Iteratee, Enumerator}
import play.api.libs.json.Json
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

  def show = Action{ implicit request =>
    if(request.session.get("id").isDefined)
      Ok(views.html.language(request.session.get("admin").get)(langForm)(updateForm))
      else
      Redirect(routes.HomeController.show)
  }

  def getById(id:Int) = Action.async{implicit request =>
    languageRepo.getByUserId(id).map {
      list => Ok(views.html.languageTable("")(list)).as("text/html")
    }
  }

  def getLanguage = Action.async{implicit request =>
    languageRepo.getByUserId(Integer.parseInt(request.session.get("id").get)).map {
      list => Ok(views.html.languageTable(request.session.get("admin").get)(list)).as("text/html")
    }
  }

  def getJson(id:Int) = Action.async{implicit request =>
    languageRepo.getById(id).map{ assignment =>
      val jsonObj = Json.obj(
        "id" -> assignment.get.id.toString,
        "name" -> assignment.get.name,
        "fluency" -> assignment.get.fluency
      )
      Ok(jsonObj)
    }
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
