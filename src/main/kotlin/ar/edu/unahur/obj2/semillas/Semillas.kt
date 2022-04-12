package ar.edu.unahur.obj2.semillas

abstract class Planta(val anioSemilla : Int, var altura: Double) {
    object Constantes {
        val HORAS_TOLERADAS = 9
    }
    open fun horasDeSolTolera() = 7
    private fun esFuerte() = horasDeSolTolera() > Constantes.HORAS_TOLERADAS
    open fun daSemilla() = esFuerte() || condAlternativa()
    abstract fun espacio() : Double
    abstract fun condAlternativa() : Boolean
}

open class Menta(anioSemilla: Int, altura: Double) : Planta(anioSemilla, altura){
    override fun espacio() = altura + 1.0
    override fun condAlternativa() = altura > 0.4
}

open class Soja(anioSemilla: Int, altura: Double) : Planta(anioSemilla,altura){
    override fun horasDeSolTolera(): Int {
        return (if (altura < 0.5) {6}
        else if (altura in 0.5..1.0){8}
        else {12})
    }
    override fun condAlternativa() = anioSemilla > 2007 && altura in 0.75..0.9
    override fun espacio() = altura / 2
}

open class Quinoa(anioSemilla: Int, altura: Double, var espacio: Double): Planta(anioSemilla,altura){
    override fun horasDeSolTolera(): Int {
        return (if(espacio() < 0.3){
            10
        }else{
            super.horasDeSolTolera()
        })
    }
    override fun condAlternativa() = anioSemilla in 2001..2008
    override fun espacio() = espacio

}

class SojaTrangenica(anioSemilla: Int,altura: Double) : Soja(anioSemilla,altura){
    override fun daSemilla() = false
}
class Peperina(anioSemilla: Int, altura: Double) : Menta(anioSemilla,altura){
    override fun espacio() = super.espacio() * 2
}