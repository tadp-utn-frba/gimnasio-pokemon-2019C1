package tadp

sealed trait Estado

case object OK extends Estado
case class Dormido(actIgnoradas: Int = 0) extends Estado
case object Paralizado extends Estado
case object KO extends Estado
