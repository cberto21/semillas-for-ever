package ar.edu.unahur.obj2.semillas

import io.kotest.assertions.throwables.shouldThrowMessage
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe


class PlantasTest : DescribeSpec({
    val menta = Menta(2021, 1.0)
    val mentita = Menta(2021, 0.3)
    val sojaAlta = Soja(2008, 0.8)
    val sojaBaja = Soja(2009, 0.6)
    val sojaTransgenica = SojaTrangenica(2005, 0.8)
    val quinoaReducida = Quinoa(2010, 0.9, 0.2)
    val quinoaExtendida2010 = Quinoa(2010, 0.9, 0.9)
    val quinoaExtendida2006 = Quinoa(2006, 0.9, 0.9)
    val peperina = Peperina(2021, 0.3)


    // Testear Menta
    describe("Una menta de 1mt debería dar semillas") {
        menta.daSemilla().shouldBeTrue()
    }
    describe("Una menta de 1mt debería ocupar 2mt2") {
        menta.espacio().shouldBe(2.0)
    }
    describe("Una menta de 0.3mt no debería dar semillas") {
        mentita.daSemilla().shouldBeFalse()
    }
    describe("Una menta de 0.3mt debería ocupar 1.3m2") {
        mentita.espacio().shouldBe(1.3)
    }
    describe("Una soja de 0.6 mt debería tener una tolerancia al sol de 8hs") {
        sojaBaja.horasSolQueTolera().shouldBe(8)
    }
    describe("Una soja de 0.6 mt y de semilla 2009 no debería dar semillas") {
        sojaBaja.daSemilla().shouldBeFalse()
    }
    describe("Una soja de 0.6 mt debería ocupar 0.3mt2") {
        sojaBaja.espacio().shouldBe(0.3)
    }
    describe("Una soja de 0.8 mt debería tener una tolerancia al sol de 8hs") {
        sojaAlta.horasSolQueTolera().shouldBe(8)
    }
    describe("Una soja de 0.8 mt y de semilla 2008 debería dar semillas") {
        sojaAlta.daSemilla().shouldBeTrue()
    }
    describe("Una soja de 0.8 mt debería ocupar 0.4mt2") {
        sojaAlta.espacio().shouldBe(0.4)
    }
    describe("Una quinoa que ocupa 0.2mt2 y con origen 2010 debería dar semillas") {
        quinoaReducida.daSemilla().shouldBeTrue()
    }
    describe("Una quinoa que ocupa 0.9mt2 y con origen 2010 no debería dar semillas") {
        quinoaExtendida2010.daSemilla().shouldBeFalse()
    }
    describe("Una quinoa que ocupa 0.9mt2 y con origen 2006 debería dar semillas") {
        quinoaExtendida2006.daSemilla().shouldBeTrue()
    }
    describe("Una soja transgénica nunca da semillas, aunque cumpla la condición general") {
        sojaTransgenica.daSemilla().shouldBeFalse()
    }
    describe("Una peperina debe ocupar el doble de espacio que la menta de mismas características") {
        peperina.espacio().shouldBe(mentita.espacio() * 2)
    }


})

class ParcelaTest : DescribeSpec({
    val menta = Menta(2021, 1.0)
    val mentita = Menta(2021, 0.3)
    val sojaAlta = Soja(2008, 0.8)
    val sojaBaja = Soja(2009, 0.6)
    describe("Una parcela de ancho: 20m y largo: 1m tiene una superficie de 20mts2") {
        val parcela = Parcela(20.00, 1.00, 10, mutableListOf(sojaBaja))
        parcela.superficie().shouldBe(20)
    }
    describe("Una parcela de ancho: 20m y largo: 1m tolera hasta 4 plantas") {
        val parcela = Parcela(20.00, 1.00, 10, mutableListOf(sojaBaja))
        parcela.cantidadMaximaPlantasTolerada().shouldBe(4)
    }
    describe("Una parcela de ancho: 1m y largo: 9m tolera hasta 12 plantas") {
        val parcela = Parcela(1.00, 9.00, 10, mutableListOf(menta))

        parcela.cantidadMaximaPlantasTolerada().shouldBe(12)
    }
    describe("Una parcela que recibe 10hs de sol tiene complicaciones con una menta de 0.3mt de altura") {
        val parcela = Parcela(1.00, 3.00, 10, mutableListOf(mentita))
        print("horas planta " + mentita.horasSolQueTolera())
        print("horas parcela " + parcela.horasSolQueRecibe)
        parcela.tieneComplicaciones().shouldBeTrue()
    }
    describe("Una parcela que recibe 4hs de sol no tiene problemas con una Soja de 0.8mt de altura") {
        val parcela = Parcela(1.00, 3.00, 4, mutableListOf(sojaAlta))

        parcela.plantar(sojaAlta)
        parcela.tieneComplicaciones().shouldBeFalse()
    }
    describe("Una parcela de ancho: 20m y largo: 1m no puede recibir más de 4 plantas") {
        val parcela = Parcela(20.00, 1.00, 10, mutableListOf(sojaBaja, menta, mentita, sojaAlta))

        shouldThrowMessage("Ya se alcanzó la cantidad máxima de plantas permitidas en la parcela.") {
            parcela.plantar(menta)

        }


    }
    describe("Una parcela que recibe 10hs de sol no permite plantarle soja de 0.6") {
        val parcela = Parcela(10.00, 1.00, 10, mutableListOf(sojaBaja))
        shouldThrowMessage("El sol que recibe la parcela supera la cantidad de horas que la planta tolera") {
            parcela.plantar(sojaBaja)

        }
    }
})

class ParcelaIdealTest : DescribeSpec({
    val menta = Menta(2021, 1.6)
    val peperina = Peperina(2021, 1.0)
    val quinoa = Quinoa(2021, 1.0, 2.00)
    val sojacomun = Soja(2021, 0.8)
    val sojatrans = SojaTrangenica(2021, 1.0)

    describe("MENTA: Una parcela con superficie menor a 6m2 NO es ideal") {
        val parcela = Parcela(5.00, 1.00, 10, mutableListOf(menta))
        menta.esIdeal(parcela).shouldBeFalse()

    }
    describe("MENTA: Una parcela con superficie mayor a 6m2 ES ideal") {
        val parcela = Parcela(7.00, 1.00, 10, mutableListOf(menta))
        menta.esIdeal(parcela).shouldBeTrue()
    }
    describe("PEPERINA: Una parcela con superficie menor a 6m2 NO es ideal") {
        val parcela = Parcela(5.00, 1.00, 10, mutableListOf(menta))
        peperina.esIdeal(parcela).shouldBeFalse()
    }
    describe("PEPERINA: Una parcela con superficie mayor a 6m2 ES ideal") {
        val parcela = Parcela(7.00, 1.00, 10, mutableListOf(menta))
        peperina.esIdeal(parcela).shouldBeTrue()
    }
    describe("QUINOA: Una parcelas con plantas más altas de 1.5m NO es ideal") {
        val parcela = Parcela(7.00, 1.00, 10, mutableListOf(menta))
        quinoa.esIdeal(parcela).shouldBeFalse()
    }
    describe("QUINOA: Una parcelas con plantas más bajas de 1.5m ES ideal") {
        val parcela = Parcela(7.00, 1.00, 10, mutableListOf(peperina))
        quinoa.esIdeal(parcela).shouldBeTrue()
    }
    describe("SOJA COMUN: Una parcela que recibe exactamente la mismas horas de sol que toleera ES ideal") {
        val parcela = Parcela(7.00, 1.00, 8, mutableListOf(peperina))
        sojacomun.esIdeal(parcela).shouldBeTrue()
    }
    describe("SOJA COMUN: Una parcela que recibe más o menos que las horas de sol que tolera NO es ideal") {
        val parcela = Parcela(7.00, 1.00, 12, mutableListOf(peperina))
        sojacomun.esIdeal(parcela).shouldBeFalse()
    }
    describe("SOJA TRANSGÉNICA: Una parcela con máxima cantidad de plantas > 1 NO es ideal") {

        val parcela = Parcela(10.00, 1.00, 10, mutableListOf(peperina))
        sojatrans.esIdeal(parcela).shouldBeFalse()
    }
    describe("SOJA TRANSGÉNICA: Una parcela con máxima cantidad de plantas es igual 1 ES ideal") {
        val parcela = Parcela(5.00, 1.00, 10, mutableListOf(peperina))
        sojatrans.esIdeal(parcela).shouldBeTrue()
    }
})

class AsociacionTest : DescribeSpec({
    describe("Una planta se asocia bien en una parcela ecológica si ésta no tiene complicaciones y es ideal para la planta") {
        val sojaAlta = Soja(2008, 0.8)
        val parcela = ParcelaEcologica(1.00, 3.00, 4, mutableListOf(sojaAlta))
        parcela.plantar(sojaAlta)
        parcela.seAsociaBienA(sojaAlta)
    }
    describe("Una planta se asocia bien en una parcela industrial si ésta tiene como máximo 2 plantas y la planta en cuestión es fuerte") {
        val sojacomun = Soja(2021, 1.8)
        val parcela = ParcelaIndustrial(10.00, 2.00, 12, mutableListOf(sojacomun))
        parcela.seAsociaBienA(sojacomun).shouldBeTrue()

    }
})

class EstadisticasIntaTest : DescribeSpec({
    val menta = Menta(2021, 1.0)
    val mentita = Menta(2021, 0.3)
    val sojaAlta = Soja(2008, 0.8)
    val sojaBaja = Soja(2009, 0.6)
    val peperina = Peperina(2021, 1.0)
    val quinoa = Quinoa(2021, 1.0, 2.00)
    val sojacomun = Soja(2021, 0.8)
    val sojatrans = SojaTrangenica(2021, 1.0)
    val parcelaInd = ParcelaIndustrial(10.00, 2.00, 12, mutableListOf(menta, mentita))
    val parcelaEco = ParcelaEcologica(1.00, 3.00, 4, mutableListOf(sojaAlta, sojaBaja))
    val parcelaStandard = Parcela(7.00, 1.00, 8, mutableListOf(peperina, quinoa, sojacomun, sojatrans, mentita))
    describe("Inta con 2 parcelas con dos plantas cada una y una con 5 plantas, debería tener de promedio 3 plantas") {


        val inta = INTA(mutableListOf(parcelaInd, parcelaEco, parcelaStandard))
        inta.promedioPlantasPorParcela().shouldBe(3)
    }
    describe("Inta con 2 parcelas con dos plantas cada una, debería tener de promedio 2 plantas") {


        val inta = INTA(mutableListOf(parcelaInd, parcelaEco))
        inta.promedioPlantasPorParcela().shouldBe(2)
    }
})