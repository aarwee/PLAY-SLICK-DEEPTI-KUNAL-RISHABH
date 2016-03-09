package models.connection

import slick.driver.PostgresDriver

/**
  * Created by kunal on 7/3/16.
  */
trait PostgresComponent extends DBComponent {

  val driver = PostgresDriver

  import driver.api._

  val db: Database = Postgres.connectionPool

}

private[connection] object Postgres {

  import slick.driver.PostgresDriver.api._
  val connectionPool = Database.forConfig("postgres")

}