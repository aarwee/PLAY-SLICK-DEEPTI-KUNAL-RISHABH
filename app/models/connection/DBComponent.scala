package models.connection
/**
  * Created by kunal on 7/3/16.
  */
import slick.driver.JdbcProfile
trait DBComponent {

  val driver: JdbcProfile

  import driver.api._

  val db: Database

}