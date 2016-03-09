package controllers

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
class ProgLanguageController extends Controller {


  val progForm = Form{

    tuple(
      "id" -> nonEmptyText,
      "name"  -> nonEmptyText,
      "fluency" ->nonEmptyText
    )
  }

  def show = Action{implicit request =>

    Ok(views.html.proglanguage(" ")(progForm))
  }

}


