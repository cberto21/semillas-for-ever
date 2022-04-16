package ar.edu.unahur.obj2.semillas

open class Parcela(var ancho: Double, var largo: Double, var horasSolQueRecibe: Int, var plantas : MutableList<Planta>){
    fun superficie() = ancho * largo
    fun cantDePlantasEnParcela() = plantas.size.toDouble()

    fun cantidadMaximaPlantasTolerada() = if (ancho > largo) { superficie() / 5 } else { (superficie()/3) + largo }

    fun agregarPlanta(unaPlanta: Planta) {
        plantas.add(unaPlanta)
    }

    fun plantar(unaPlanta: Planta){
        if (condicionParaPlantar(unaPlanta)){
            throw Exception("Ya se alcanzó la cantidad máxima de plantas permitidas en la parcela.")
        } else if(unaPlanta.horasSolQueTolera() <= horasSolQueRecibe+2) {
            throw Exception("El sol que recibe la parcela supera la cantidad de horas que la planta tolera")
        } else{
            agregarPlanta(unaPlanta)

        }
    }

    fun quitar(unaPlanta: Planta) = plantas.remove(unaPlanta)

    fun condicionParaPlantar(unaPlanta: Planta) = cantidadMaximaPlantasTolerada() < cantDePlantasEnParcela() || horasSolQueRecibe > unaPlanta.horasSolQueTolera()+2

    fun tieneComplicaciones() = plantas.any{ it.horasSolQueTolera() > horasSolQueRecibe}



    open fun seAsociaBienA(unaPlanta: Planta) = false

    fun porcentajeBienAsociada() = plantas.count{n ->this.seAsociaBienA(n)} / plantas.size * 100.0
}

class ParcelaEcologica(ancho: Double, largo: Double, horasDeSol: Int, listaDePlantas: MutableList<Planta>) : Parcela(ancho, largo,horasDeSol,listaDePlantas){
    override fun seAsociaBienA(unaPlanta: Planta) = !tieneComplicaciones() && unaPlanta.esIdeal(this)
}

class ParcelaIndustrial(ancho: Double, largo: Double, horasDeSol: Int, listaDePlantas: MutableList<Planta>) : Parcela(ancho, largo,horasDeSol,listaDePlantas){
    override fun seAsociaBienA(unaPlanta: Planta) = plantas.size < 2 && unaPlanta.esFuerte()
}
