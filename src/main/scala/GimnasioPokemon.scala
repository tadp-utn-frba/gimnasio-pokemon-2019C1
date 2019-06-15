package gimnasioPokemon

import scala.util.Try
import scala.util.Success
import scala.util.Failure

object GimnasioPokemon {

  case class Pokemon(
                      experiencia: Int,
                      energia: Int,
                      especie: ???) {

  }

  trait TipoPokemon

  case object Fuego extends TipoPokemon

  case object Volador extends TipoPokemon

  case object Pelea extends TipoPokemon

  case object Fantasma extends TipoPokemon

  case object Agua extends TipoPokemon

  case object Tierra extends TipoPokemon

  case object Roca extends TipoPokemon

}