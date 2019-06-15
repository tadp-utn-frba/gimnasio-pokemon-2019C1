package tadp

case class Caract(energiaMax: Int, fuerza: Int, vel: Int)

case class Pokemon(xp: Int,
                   energia: Int,
                   caract: Caract,
                   especie: Especie,
                   estado: Estado = OK)
