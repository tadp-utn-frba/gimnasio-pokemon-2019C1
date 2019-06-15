package tadp

object Gimnacio {
  type Actividad = Pokemon => Option[Pokemon]

  val descansar: Actividad = pokemon => Some(
    pokemon.copy(energia = pokemon.caract.energiaMax)
  )

  def pesas(kilos: Int): Actividad = {
    case Pokemon(_, _, _, Especie(Fantasma, _)) => None
    case p@Pokemon(_, energia, Caract(_, fuerza, _), _) if kilos > 10 * fuerza =>
      Some(p.copy(energia = energia - 10))
    case p@Pokemon(xp, _, _, Especie(Pelea, _) | Especie(_, Some(Pelea))) =>
      Some(p.copy(xp = xp + (kilos * 2)))
    case p => Some(p.copy(xp = p.xp + kilos))
  }

  def nadar(minutos: Int): Actividad = {
    case p@Pokemon(xp, energia, caract, Especie(Agua, _)) => Some(p.copy(
      energia = energia - minutos,
      xp = xp + (200 * minutos),
      caract = caract.copy(vel = caract.vel + (minutos / 60))
    ))
    case p@Pokemon(xp, energia, _, _) => Some(p.copy(
      energia = energia - minutos,
      xp = xp + (200 * minutos)
    ))
  }
}
