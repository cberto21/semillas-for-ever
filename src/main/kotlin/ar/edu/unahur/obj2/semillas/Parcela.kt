package ar.edu.unahur.obj2.semillas

import kotlin.math.roundToInt

open class Parcela(var ancho: Double, var largo: Double, var horasSolQueRecibe: Int, var plantas: MutableList<Planta>) {
    fun superficie(): Double = ancho * largo
    fun cantDePlantasEnParcela(): Int = plantas.size

    fun cantidadMaximaPlantasTolerada(): Int = if (ancho > largo) {
        (superficie() / 5).roundToInt()
    } else {
        ((superficie() / 3) + largo).roundToInt()
    }

    fun plantar(unaPlanta: Planta) {
       if (excedioElMaximoDePlantas()) {
            throw Error("Ya se alcanzó la cantidad máxima de plantas permitidas en la parcela.")
        }
        if (excedeElSolQueTolera(unaPlanta)) {
            throw Error("El sol que recibe la parcela supera la cantidad de horas que la planta tolera")
        }
        plantas.add(unaPlanta)
    }

    fun quitar(unaPlanta: Planta) = plantas.remove(unaPlanta)

    fun sePuedePlantar(unaPlanta: Planta): Boolean = !(excedioElMaximoDePlantas() || excedeElSolQueTolera(unaPlanta))
    fun excedioElMaximoDePlantas(): Boolean = cantDePlantasEnParcela() >= cantidadMaximaPlantasTolerada()
    fun excedeElSolQueTolera(unaPlanta: Planta): Boolean = horasSolQueRecibe >= unaPlanta.horasSolQueTolera() + 2
    fun tieneComplicaciones(): Boolean = plantas.any { it.horasSolQueTolera() < horasSolQueRecibe }


    open fun seAsociaBienA(unaPlanta: Planta) = false

    fun porcentajeBienAsociada() = plantas.count { n -> this.seAsociaBienA(n) } / plantas.size * 100.0
}

class ParcelaEcologica(ancho: Double, largo: Double, horasDeSol: Int, listaDePlantas: MutableList<Planta>) :
    Parcela(ancho, largo, horasDeSol, listaDePlantas) {
    override fun seAsociaBienA(unaPlanta: Planta) = !tieneComplicaciones() && unaPlanta.esIdeal(this)
}

class ParcelaIndustrial(ancho: Double, largo: Double, horasDeSol: Int, listaDePlantas: MutableList<Planta>) :
    Parcela(ancho, largo, horasDeSol, listaDePlantas) {
    override fun seAsociaBienA(unaPlanta: Planta) = plantas.size <= 2 && unaPlanta.esFuerte()
}
