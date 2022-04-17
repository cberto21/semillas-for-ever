package ar.edu.unahur.obj2.semillas
class INTA(var parcelas: MutableCollection<Parcela>) {
    fun promedioPlantasPorParcela(): Int {
        return parcelas.sumBy { it.cantDePlantasEnParcela() } / parcelas.size
    }

    fun masSustentable() =
        parcelasSustentables().find { c -> c.porcentajeBienAsociada() == parcelasSustentables().maxOf { c.porcentajeBienAsociada() } }

    fun parcelasSustentables() = parcelas.filter { it.cantDePlantasEnParcela() >= 4 }
}