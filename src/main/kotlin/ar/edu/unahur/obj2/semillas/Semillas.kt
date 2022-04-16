package ar.edu.unahur.obj2.semillas

abstract class Planta(val anioSemilla : Int, var altura: Double) {
    object Constantes {
        val HORAS_TOLERADAS = 9
    }
    open fun horasSolQueTolera() = 7
     fun esFuerte() = horasSolQueTolera() > Constantes.HORAS_TOLERADAS
    open fun daSemilla() = esFuerte() || condAlternativa()
    abstract fun condAlternativa() : Boolean s

    abstract fun esIdeal(unaParcela: Parcela): Boolean
}

open class Menta(anioSemilla: Int, altura: Double) : Planta(anioSemilla, altura){
    open fun espacio() = altura + 1
    override fun condAlternativa() = altura > 0.4
    override fun esIdeal(unaParcela: Parcela) = unaParcela.superficie() > 6
}

class Peperina(anioSemilla: Int, altura: Double) : Menta(anioSemilla,altura){
    override fun espacio() = super.espacio() * 2
}


open class Soja(anioSemilla: Int, altura: Double) : Planta(anioSemilla,altura){

    fun espacio() = altura/2
    override fun horasSolQueTolera(): Int {
        return (if (altura < 0.5) {6}
        else if (altura in 0.5..1.0){8}
        else {12})
    }
    override fun condAlternativa() = anioSemilla > 2007 && altura in 0.75..0.9

    override fun esIdeal(unaParcela: Parcela) = unaParcela.horasSolQueRecibe == horasSolQueTolera()

}

class SojaTrangenica(anioSemilla: Int,altura: Double) : Soja(anioSemilla,altura){
    override fun daSemilla() = false

    override fun esIdeal(unaParcela: Parcela) = unaParcela.cantidadMaximaPlantasTolerada() == 1.0
}


open class Quinoa(anioSemilla: Int, altura: Double, var espacio: Double): Planta(anioSemilla,altura){
    override fun horasSolQueTolera()= if(espacio < 0.3) {10} else {super.horasSolQueTolera()}
    override fun condAlternativa() = anioSemilla in 2001..2008
    override fun esIdeal(unaParcela: Parcela) = unaParcela.plantas.all{ it.altura <= 1.5}

}


