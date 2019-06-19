package enclase

import enclase.Pokemon.Actividad

import scala.util.{Failure, Success, Try}

case class Pokemon(xp: Int,
                   energia: Int,
                   eMax: Int,
                   fuerza: Int,
                   vel: Int,
                   especie: Especie,
                   estado: Estado = OK) {

  def nadar(minutos: Int): Try[Pokemon] = actividad { () =>
    if (especie.pierdeContraAgua) {
      Success(copy(estado = KO))
    } else {
      Success(copy(
        energia = energia - minutos,
        xp = xp + 200 * minutos,
        vel =
          if (especie.tipo == Agua)
            vel + (minutos / 60)
          else
            vel
      ))
    }
  }

  private def actividad(actividad: () => Try[Pokemon]): Try[Pokemon] =
    Try(this.estado match {
      case KO => throw new Exception("Está KO")
      case Dormido(1) => copy(estado = OK)
      case Dormido(x) => copy(estado = Dormido(x - 1))
      case _ => actividad().get
    })

  lazy val descansar: Try[Pokemon] = actividad { () =>
    Success(copy(
      energia = eMax,
      estado =
        if (estado == OK && (energia.toFloat / eMax) < 0.5)
          Dormido(3)
        else
          estado
    ))
  }

  def pesas(kilos: Int): Try[Pokemon] = actividad { () =>
    this match {
      case Pokemon(_, _, _, _, _, Especie(Fantasma, _), _) =>
        Failure(new Exception("Esto es un fantasma amiguito!!!"))
      case Pokemon(_, _, _, _, _, _, Paralizado) =>
        Success(copy(estado = KO))
      case Pokemon(_, _, _, fuerza, _, _, _) if kilos > 10 * fuerza =>
        Success(copy(
          energia = energia - 10,
          estado = Paralizado
        ))
      case Pokemon(_, _, _, _, _, Especie(Pelea, _) | Especie(_, Some(Pelea)), _) =>
        Success(copy(xp = xp + kilos * 2))
      case _ =>
        Success(copy(xp = xp + kilos))
    }
  }

}

sealed trait Estado
case object OK extends Estado
case class Dormido(cantIgnoradas: Int) extends Estado
case object Paralizado extends Estado
case object KO extends Estado

case class Especie(tipo: Tipo,
                   tipoSec: Option[Tipo] = None) {

  private val pierdenContraAgua: Set[Tipo] =
    Set(Roca, Fuego, Tierra)

  val pierdeContraAgua: Boolean =
    pierdenContraAgua.contains(tipo) ||
      tipoSec.exists(pierdenContraAgua.contains)
}

object Pikachu extends Especie(Electrico)

sealed trait Tipo
case object Agua extends Tipo
case object Electrico extends Tipo
case object Fuego extends Tipo
case object Tierra extends Tipo
case object Pelea extends Tipo
case object Fantasma extends Tipo
case object Roca extends Tipo

abstract class ActividadX(pokemon: Pokemon,
                          condicion: Pokemon => Boolean)
  extends (() => Try[Pokemon]) {

  override def apply(): Try[Pokemon] = {
    if (condicion(pokemon))
      hacelo
    else
      Failure(new Exception("no pasa la condicion"))
  }
  def hacelo: Try[Pokemon]
}
class Nadar extends ActividadX(???, ???) {
  override def hacelo: Try[Pokemon] = ???
}
class Dormir extends ActividadX(???, ???) {
  override def apply(): Try[Pokemon] =
  //otra cosa
    ???
  override def hacelo: Try[Pokemon] = ???
}


object Actividad {
  val d: () => Try[Pokemon] = new Dormir

  def apply(actividad: Actividad): Actividad = {
    pokemon =>
      if (pokemon.estado == KO) Failure(new Exception("Esta KO, no puedo"))
      else actividad(pokemon)
  }
}

object Pokemon {
  type Actividad = Pokemon => Try[Pokemon]


  def actividad(f: Actividad): Actividad = ???

  val Nadar2: Actividad = p => Success(p)
  //  case class Nadar extends Actividad

  val descansar = Actividad { pokemon =>
    import pokemon._
    Success(copy(energia = eMax))
  }

  val descansar2: Actividad = pokemon => {
    import pokemon._
    Success(copy(energia = eMax))
  }


}

