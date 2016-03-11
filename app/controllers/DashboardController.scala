package controllers

import models.User
import play.api.mvc.{Session, Action, Controller}
import play.api.Play.current
import play.api.i18n.Messages.Implicits._
/**
  * Created by kunal on 8/3/16.
  */
class DashboardController extends Controller {

  def show = Action{ implicit request =>
         Ok(views.html.dashboard(request.session.get("admin").get))

//         Ok(views.html.dashboard("user"))
  }
}
