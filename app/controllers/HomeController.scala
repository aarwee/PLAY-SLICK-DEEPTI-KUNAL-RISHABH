
package controllers

import com.google.inject.Inject
import models.connection.DBComponent
import models.{UserRepo, User}
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
class HomeController @Inject()(userRepo: UserRepo) extends Controller {

  val loginForm = Form(

    tuple(
      "email" -> nonEmptyText,
      "password" -> text

    )
  )

  def show = Action { implicit request =>
    Ok(views.html.home(loginForm))
  }


  def validateUser = Action { implicit request =>
//    userRepo.create()
    loginForm.bindFromRequest.fold(
      badform => {
        BadRequest(views.html.home(badform))
      },
      validform => {
        //        Redirect(routes.DashboardController.show("admin"))
        val user: Option[User] = Await.result(userRepo.getUser(validform._1),2 second)
        if (user.isDefined && user.get.password == validform._2)
        {
          Redirect(routes.DashboardController.show(user.get.admin.toString)).withSession("id"->user.get.id.toString)
        }
        else {
          Redirect(routes.HomeController.show).flashing("error"->"Invalid Email or password")
        }
      }
    )

  }


}







