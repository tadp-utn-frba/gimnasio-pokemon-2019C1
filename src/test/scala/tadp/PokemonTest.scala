package tadp

import org.scalatest.{FreeSpec, Matchers}
import tadp.Gimnacio._

class PokemonTest extends FreeSpec with Matchers {

  "Un Pokemon" - {
    "debería recuperar la energia maxima al descansar" in {
      descansar(Pokemon(100, 8, Caract(100, 20, 4), Charizard)) shouldEqual
        Some(Pokemon(100, 100, Caract(100, 20, 4), Charizard))
    }

    "al levantar pesas" - {
      "gana xp" in {
        pesas(10)(
          Pokemon(100, 8, Caract(100, 20, 4), Especie(Agua, None))
        ) shouldEqual
          Some(
            Pokemon(110, 8, Caract(100, 20, 4), Especie(Agua, None))
          )
      }

      "tipo pelea debería ganar doble de puntos" in {
        val peleaPrinc = Pokemon(100, 8, Caract(100, 20, 4), Especie(Pelea, None))
        val peleaSec = Pokemon(100, 8, Caract(100, 20, 4), Especie(Agua, Some(Pelea)))

        pesas(10)(peleaPrinc) shouldEqual
          Some(Pokemon(120, 8, Caract(100, 20, 4), Especie(Pelea, None)))
        pesas(10)(peleaSec) shouldEqual
          Some(Pokemon(120, 8, Caract(100, 20, 4), Especie(Agua, Some(Pelea))))
      }

      "debería perder energia si carga más de lo que puede" in {
        pesas(201)(Pokemon(100, 80, Caract(100, 20, 4), Especie(Agua, None))) shouldEqual
          Some(Pokemon(100, 70, Caract(100, 20, 4), Especie(Agua, None)))
      }

      "no debería poder si es fantasma" in {
        pesas(201)(Pokemon(100, 80, Caract(100, 20, 4), Especie(Fantasma, None))) shouldEqual
          None
      }
    }

    "al nadar" - {
      "pierde energía y gana xp" in {
        nadar(250)(Pokemon(100, 500, Caract(100, 20, 4), Especie(Fantasma, None))) shouldEqual
          Some(Pokemon(100 + 200 * 250, 250, Caract(100, 20, 4), Especie(Fantasma, None)))
      }
      "los de agua ganan velocidad" in {
        nadar(250)(Pokemon(100, 500, Caract(100, 20, 4), Especie(Agua, None))) shouldEqual
          Some(Pokemon(100 + 200 * 250, 250, Caract(100, 20, 8), Especie(Agua, None)))
      }
    }
  }

}
