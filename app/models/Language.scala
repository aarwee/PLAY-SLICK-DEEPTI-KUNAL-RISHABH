package models

import com.google.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

import scala.concurrent.Future


/**
  * Created by kunal on 9/3/16.
  */


case class Language (name:String,fluency:String ,userId:Int,id:Int=0)



class LanguageRepo @Inject()(protected val dbConfigProvider:DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] with  LanguageTable{
  import driver.api._

  def getAll: Future[List[Language]] = {

    db.run{languageTable.to[List].result}
  }

  def create() ={
    db.run{languageTable.schema.create}
  }
  def add(name:String,fluency:String,userId:Int) = {
    print(name,fluency,userId)
    db.run{languageTable.returning(languageTable.map(_.id))  += Language(name,fluency,userId)}
  }

  def delete(id:Int):Future[Int] ={
    db.run{languageTable.filter{_.id === id}.delete}
  }


  def update(id:Int,name:String,fluency:String,userId:Int):Future[Int] =
  {
    db.run{ languageTable.filter(_.id === id).update(Language(name,fluency,userId,id))}

  }


}

trait LanguageTable  {
  self: HasDatabaseConfigProvider[JdbcProfile] =>

  import driver.api._

  class LanguageTable(tag:Tag) extends Table[Language](tag,"language"){
    val id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    val name = column[String]("name", O.SqlType("VARCHAR(200)"))
    val fluency = column[String]("fluency", O.SqlType("VARCHAR(20)"))
    val userId = column[Int]("user_id")
    def * = (name,fluency,userId,id) <>(Language.tupled, Language.unapply)

  }
  val languageTable = TableQuery[LanguageTable]
}


