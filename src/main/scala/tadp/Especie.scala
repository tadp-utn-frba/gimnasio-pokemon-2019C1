package tadp

case class Especie(tipo: Tipo, tipoSecundario: Option[Tipo] = None)
object Charizard extends Especie(Fuego, Some(Volador))
object Machop extends Especie(Pelea)
object Gengar extends Especie(Fantasma)
object Magikarp extends Especie(Agua)
