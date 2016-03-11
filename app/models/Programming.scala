package models

import com.google.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

import scala.concurrent.Future


/**
  * Created by kunal on 9/3/16.
  */


case class Programming (name:String,fluency:String ,userId:Int,id:Int=0)



class ProgrammingRepo @Inject()(protected val dbConfigProvider:DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] with ProgrammingTable{
  import driver.api._

  def getAll: Future[List[Programming]] = {
    db.run{programmingTable.to[List].result}
  }

  def create() ={
    db.run{programmingTable.schema.create}
  }
  def add(name:String,fluency:String,userId:Int) = {
    db.run{programmingTable  += Programming(name, fluency,userId)}
  }

  def delete(id:Int):Future[Int] ={
    db.run{programmingTable.filter{_.id === id}.delete}
  }
  def update(id:Int,name:String,fluency:String,userId:Int):Future[Int] =
  {
    db.run{ programmingTable.filter(_.id === id).update(Programming(name,fluency,userId,id))}

  }


}

trait ProgrammingTable  {
  self: HasDatabaseConfigProvider[JdbcProfile] =>

  import driver.api._

  class ProgrammingTable(tag:Tag) extends Table[Programming](tag,"programming"){
    val id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    val fluency = column[String]("fluency", O.SqlType("VARCHAR(200)"))
    val userId = column[Int]("user_id")
    val name = column[String]("name", O.SqlType("VARCHAR(200)"))
    def * = (name,fluency,userId,id) <>(Programming.tupled, Programming.unapply)

  }
  val programmingTable = TableQuery[ProgrammingTable]
}