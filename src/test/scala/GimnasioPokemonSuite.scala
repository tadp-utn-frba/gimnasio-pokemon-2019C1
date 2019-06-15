package gimnasioPokemon

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import gimnasioPokemon.GimnasioPokemon._

class GimnasioPokemonSuite extends FlatSpec with Matchers {

  "cuando un pokemon descansa" should
    "su energía debería ser igual a la máxima" in {
  }

  "cuando un pokemon levanta pesas correctamente" should
    "gana un punto de experiencia por cada kg levantado" in {
  }

  "cuando un pokemon de pelea levanta pesas correctamente" should
    "gana dos puntos de experiencia por cada kg levantado" in {

  }

  "un pokemon fantasma" should
    "no poder levantar pesas" in {

  }

  "cuando un pokemon levanta más de lo que puede" should
    "pierde 10 de energia y no gana experiencia" in {


  }

  "cuando un pokemon nada 1 minuto" should
    "pierde un punto de energia y gana 200 de experiencia" in {
  }

  "cuando un pokemon de tipo agua nada 1 hora" should
    """pierde 60 puntos de energia y
       gana 12000 de experiencia y gana un punto de velocidad""" in {


  }

  "cuando que pierde contra el agua nada" should
    "queda en KO y no gana experiencia" in {



  }

  "cuando un pokemon paralizado levanta pesas" should
    "no gana experiencia y queda KO" in {


  }

  "cuando un pokemon descansa, está normal y tiene menos del 50% de energia" should
    "recupera su energía y queda Dormido" in {


  }

  "un pokemon dormido" should
    "se despierta después de tres actividades" in {


  }
}