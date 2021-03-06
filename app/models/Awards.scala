package models


import com.google.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

import scala.concurrent.Future


/**
  * Created by kunal on 9/3/16.
  */


case class Awards (name:String,description:String,year:String ,userId:Int,id:Int=0)



class AwardsRepo @Inject()(protected val dbConfigProvider:DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] with AwardsTable{
  import driver.api._

  def getAll: Future[List[Awards]] = {
    db.run{awardsTable.to[List].result}
  }

  def getByUserId(id:Int): Future[List[Awards]] = {
    db.run{awardsTable.filter(_.userId === id).to[List].result}
  }

  def getById(id:Int):Future[Option[Awards]] ={
    db.run{awardsTable.filter(_.id === id).result.headOption}
  }

  /*def create() ={
    db.run{awardsTable.schema.create}
  }*/
  def add(name:String,description:String,year:String,userId:Int) = {
    db.run{awardsTable += Awards(name, description,year,userId)}
  }

  def delete(id:Int):Future[Int] ={
    db.run{awardsTable.filter{_.id === id}.delete}
  }
 def update(id:Int,name:String,description:String,year:String,userId:Int):Future[Int] =
  {
    db.run{ awardsTable.filter(_.id === id).update(Awards(name,description,year,userId,id))}

  }


}

trait AwardsTable  {
  self: HasDatabaseConfigProvider[JdbcProfile] =>

  import driver.api._

  class AwardsTable(tag:Tag) extends Table[Awards](tag,"awards"){
    val id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    val description = column[String]("description", O.SqlType("VARCHAR(200)"))
    val year = column[String]("year", O.SqlType("VARCHAR(20)"))
    val userId = column[Int]("user_id")
    val name = column[String]("name", O.SqlType("VARCHAR(200)"))
    def * = (name,description,year,userId,id) <>(Awards.tupled, Awards.unapply)

  }
  val awardsTable = TableQuery[AwardsTable]
}


