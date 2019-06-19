package script

case class Especie(tipo: Tipo, tipoSecundario: Option[Tipo] = None)

object Charizard extends Especie(Fuego, Some(Volador))
object Machop extends Especie(Pelea, None)
object Gengar extends Especie(Fantasma, None)
object Magikarp extends Especie(Agua, None)
