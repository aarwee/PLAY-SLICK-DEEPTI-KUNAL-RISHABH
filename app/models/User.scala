package models


import com.google.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

import scala.concurrent.Future


/**
  * Created by kunal on 8/3/16.
  */


case class User (name:String,email:String,password:String , mobile:String, admin:Boolean,id:Int)

class UserRepo @Inject()(protected val dbConfigProvider:DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] with UserTable{
  import driver.api._

  def getAll:Future[List[User]] = {
    db.run{userTable.to[List].result}
  }

  def getUser(email:String): Future[Option[User]] = {
    db.run{userTable.filter(_.email === email).result.headOption}
  }

 /* def create() ={
    db.run{userTable.schema.create}
  }*/

}

trait UserTable  {
  self: HasDatabaseConfigProvider[JdbcProfile] =>

  import driver.api._

  class UserTable(tag:Tag) extends Table[User](tag,"users"){
    val id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    val name = column[String]("name", O.SqlType("VARCHAR(20)"))
    val email = column[String]("email", O.SqlType("VARCHAR(20)"))
    val password = column[String]("password", O.SqlType("VARCHAR(20)"))
    val mobile = column[String]("mobile", O.SqlType("VARCHAR(10)"))
    val admin = column[Boolean]("admin", O.SqlType("BOOLEAN"))
    def * = (name,email,password,mobile,admin,id) <>(User.tupled, User.unapply)

  }
  val userTable = TableQuery[UserTable]
  //  db.run{userTable.returning(userTable.map(_.id)) += User(1,"kunal","k@k.com","kunal","9999",true)}
}


