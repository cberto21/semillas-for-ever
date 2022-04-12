package ar.edu.unahur.obj2.semillas

class Parcela(var ancho: Int, var largo: Int, var horasSolQueRecibe: Int, var plantas : MutableList<Planta>){
    fun superficie(): Int{
        return ancho * largo
    }
    fun agregarPlanta(unaPlanta: Planta) {
        plantas.add(unaPlanta)
    }
    fun cantidadMaximaPlantasTolerada() = if (ancho > largo) { superficie() / 5 } else { (superficie()/3) + largo }
    fun tieneComplicaciones() = plantas.any{ planta:Planta -> planta.horasSolQueTolera() < horasSolQueRecibe}
    fun plantar(unaPlanta: Planta){
        if (plantas.size == cantidadMaximaPlantasTolerada()){
            throw Exception("Ya se alcanzó la cantidad máxima de plantas permitidas en la parcela.")
        } else if(unaPlanta.horasSolQueTolera() <= horasSolQueRecibe+2) {
            throw Exception("El sol que recibe la parcela supera la cantidad de horas que la planta tolera")
        } else{
            agregarPlanta(unaPlanta)

        }
    }
}