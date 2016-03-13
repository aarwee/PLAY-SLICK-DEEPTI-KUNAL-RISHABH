package models

import com.google.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

import scala.concurrent.Future

/**
  * Created by kunal on 10/3/16.
  */
case class Assignment (name:String,date:String,marks:Int,remarks:String,userId:Int,id:Int=0)

class AssignmentRepo @Inject()(protected val dbConfigProvider:DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] with AssignmentTable {

  import driver.api._

  def getAll: Future[List[Assignment]] = {
    db.run{assignmentTable.to[List].result}
  }



  def getById(id:Int):Future[Option[Assignment]] ={
    db.run{assignmentTable.filter(_.id === id).result.headOption}
  }

  def getByUserId(id:Int): Future[List[Assignment]] = {
    db.run{assignmentTable.filter(_.userId === id).to[List].result}
  }

 /* def create() ={
    db.run{assignmentTable.schema.create}
  }*/
  def add(name:String,date:String,marks:Int,remarks:String,userId:Int) = {
    db.run{assignmentTable += Assignment(name, date,marks,remarks,userId)}
  }

  def delete(id:Int):Future[Int] ={
    db.run{assignmentTable.filter{_.id === id}.delete}
  }
  def update(id:Int,name:String,date:String,marks:Int,remarks:String,userId:Int):Future[Int] =
  {
    db.run{ assignmentTable.filter(_.id === id).update(Assignment(name, date,marks,remarks,userId,id))}

  }
}


trait AssignmentTable{
  self:HasDatabaseConfigProvider[JdbcProfile] =>

  import driver.api._

  class AssignmentTable(tag: Tag) extends Table[Assignment](tag,"assignment"){
    val name = column[String]("name",O.SqlType("VARCHAR(20)"))
    val date = column[String]("date",O.SqlType("VARCHAR(20)"))
    val marks = column[Int]("marks")
    val remarks = column[String]("remarks",O.SqlType("VARCHAR(20)"))
    val userId = column[Int]("user_id")
    val id = column[Int]("id",O.PrimaryKey,O.AutoInc)
    def * = (name,date,marks,remarks,userId,id) <>(Assignment.tupled,Assignment.unapply)
  }
  val assignmentTable = TableQuery[AssignmentTable]
}