package tadp

object Gimnacio {
  type Actividad = Pokemon => Option[Pokemon]

  def seqActividades(activities: Actividad*): Actividad =
    activities.reduceLeft((a1, a2) => a1.andThen(_.flatMap(a2)))

  val preAll: Actividad = {
    case Pokemon(_, _, _, _, KO) => None
    case p@Pokemon(_, _, _, _, Dormido(actIgnoradas)) if actIgnoradas < 3 =>
      Some(p.copy(estado = Dormido(actIgnoradas + 1)))
    case p@Pokemon(_, _, _, _, Dormido(actIgnoradas)) =>
      Some(p.copy(estado = OK))
    case p => Some(p)
  }

  def withPre(actividad: Actividad) = seqActividades(preAll, actividad)

  val preDescansar: Actividad = {
    case p@Pokemon(_, energia, Caract(energiaMax, _, _), _, OK)
      if (energia.toFloat / energiaMax) < 0.5 =>
      Some(p.copy(estado = Dormido()))
    case p => Some(p)
  }

  val afterDescansar: Actividad = { pokemon =>
    Some(
      pokemon.copy(energia = pokemon.caract.energiaMax)
    )
  }

  val descansar: Actividad = seqActividades(preAll, preDescansar, afterDescansar)

  def pesas(kilos: Int): Actividad = withPre {
    case Pokemon(_, _, _, Especie(Fantasma, _), _) => None
    case p@Pokemon(_, _, _, _, Paralizado) =>
      Some(p.copy(
        estado = KO
      ))
    case p@Pokemon(_, energia, Caract(_, fuerza, _), _, _) if kilos > 10 * fuerza =>
      Some(p.copy(
        energia = energia - 10,
        estado = Paralizado
      ))
    case p@Pokemon(xp, _, _, Especie(Pelea, _) | Especie(_, Some(Pelea)), _) =>
      Some(p.copy(
        xp = xp + (kilos * 2)
      ))
    case p => Some(p.copy(
      xp = p.xp + kilos
    ))
  }

  def nadar(minutos: Int): Actividad = withPre {
    case p@Pokemon(_, _, _, Especie(Roca | Tierra | Fuego, _) |
                            Especie(_, Some(Roca | Tierra | Fuego)), _) =>
      Some(p.copy(
        estado = KO
      ))
    case p@Pokemon(xp, energia, caract, Especie(Agua, _), _) => Some(p.copy(
      energia = energia - minutos,
      xp = xp + (200 * minutos),
      caract = caract.copy(vel = caract.vel + (minutos / 60))
    ))
    case p@Pokemon(xp, energia, _, _, _) => Some(p.copy(
      energia = energia - minutos,
      xp = xp + (200 * minutos)
    ))
  }
}
