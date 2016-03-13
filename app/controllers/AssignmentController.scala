package controllers

import com.google.inject.Inject
import models.connection.DBComponent
import models.{AssignmentRepo, User}
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.iteratee.{Iteratee, Enumerator}
import play.api.libs.json.Json
import play.api.mvc._
import play.api.Play.current
import play.api.i18n.Messages.Implicits._
import play.api.libs.concurrent.Execution.Implicits._
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

/**
  * Created by knoldus on 8/3/16.
  */
class AssignmentController@Inject()(assignmentRepo: AssignmentRepo) extends Controller {


  val assignmentForm = Form{

    tuple(

      "name"  -> nonEmptyText,
      "date" ->text.verifying("Valid Date Required", data => validateDate(data)),
      "marks" ->number,
      "remarks" ->nonEmptyText

    )
  }
  val updateForm = Form{

    tuple(
      "id" -> nonEmptyText,
      "name"  -> nonEmptyText,
      "date" ->text.verifying("Valid Date Required", data => validateDate(data)),
      "marks" ->number,
      "remarks" ->nonEmptyText
    )
  }

  def validateDate(date: String): Boolean = {
    val dateregex = """^(19|20)\d\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$""".r
    date match {
      case dateregex(day,month,year) => true
      case _ =>false
    }
  }

  def show = Action{ implicit request =>
    if (request.session.get("id").isDefined)
      Ok(views.html.assignment(request.session.get("admin").get)(assignmentForm)(updateForm))
    else
      Redirect(routes.HomeController.show)

  }

  def getAssignment = Action.async{implicit request =>
    assignmentRepo.getByUserId(Integer.parseInt(request.session.get("id").get)).map {
      list => Ok(views.html.assignmentTable(request.session.get("admin").get)(list)).as("text/html")
    }
  }

  def getJson(id:Int) = Action.async{implicit request =>
    assignmentRepo.getById(id).map{ assignment =>
      val jsonObj = Json.obj(
        "id" -> assignment.get.id.toString,
        "name" -> assignment.get.name,
        "date" -> assignment.get.date,
        "marks" -> assignment.get.marks,
        "remarks" -> assignment.get.remarks
      )
      Ok(jsonObj)
    }
  }


  def add   = Action.async{ implicit request =>
    val userId = request.session.get("id")

    assignmentForm.bindFromRequest.fold(
      baddForm =>Future{BadRequest(" ")},
      validForm => {
        assignmentRepo.add(validForm._1, validForm._2, validForm._3, validForm._4, Integer.parseInt(userId.get)).map {
         a=> Redirect(routes.AssignmentController.show)
        }
      }

    )

  }

  def update   = Action.async{ implicit request =>
    val userId = request.session.get("id")
    updateForm.bindFromRequest.fold(
      badform =>{print(badform);Future{BadRequest("")}},
      validForm => {assignmentRepo.update(Integer.parseInt(validForm._1),validForm._2, validForm._3,validForm._4,validForm._5,Integer.parseInt(userId.get)).map{
        a=>Redirect(routes.AssignmentController.show)}
      }

    )

  }

  def delete(id:String) = Action{
    assignmentRepo.delete(Integer.parseInt(id))
    Redirect(routes.AssignmentController.show)
  }

}
