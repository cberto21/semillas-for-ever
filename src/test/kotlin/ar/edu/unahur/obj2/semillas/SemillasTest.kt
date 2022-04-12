package ar.edu.unahur.obj2.semillas

import ar.edu.unahur.obj2.ejemplo.*
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe


class SemillaTest: DescribeSpec({
    val menta = Menta(2021, 1.0)
    val mentita = Menta( 2021,0.3)
    val sojaAlta = Soja(2008,0.8)
    val sojaBaja = Soja(2009,0.6)
    val sojaTransgenica = SojaTrangenica(2005,0.8)
    val quinoaReducida = Quinoa(2010, 0.9, 0.2)
    val quinoaExtendida2010 = Quinoa(2010, 0.9, 0.9)
    val quinoaExtendida2006 = Quinoa(2006, 0.9, 0.9)
    val peperina = Peperina(2021,0.3)

    // Testear Menta
    describe("Una menta de 1mt debería dar semillas"){
        menta.daSemilla().shouldBeTrue()
    }
    describe("Una menta de 1mt debería ocupar 2mt2"){
        menta.espacio().shouldBe(2.0)
    }
    describe("Una menta de 0.3mt no debería dar semillas"){
        mentita.daSemilla().shouldBeFalse()
    }
    describe("Una menta de 0.3mt debería ocupar 1.3m2"){
        mentita.espacio().shouldBe(1.3)
    }
    describe("Una soja de 0.6 mt debería tener una tolerancia al sol de 8hs"){
        sojaBaja.horasDeSolTolera().shouldBe(8)
    }
    describe("Una soja de 0.6 mt y de semilla 2009 no debería dar semillas"){
        sojaBaja.daSemilla().shouldBeFalse()
    }
    describe("Una soja de 0.6 mt debería ocupar 0.3mt2"){
        sojaBaja.espacio().shouldBe(0.3)
    }
    describe("Una soja de 0.8 mt debería tener una tolerancia al sol de 8hs"){
        sojaAlta.horasDeSolTolera().shouldBe(8)
    }
    describe("Una soja de 0.8 mt y de semilla 2008 debería dar semillas"){
        sojaAlta.daSemilla().shouldBeTrue()
    }
    describe("Una soja de 0.8 mt debería ocupar 0.4mt2"){
        sojaAlta.espacio().shouldBe(0.4)
    }
    describe("Una quinoa que ocupa 0.2mt2 y con origen 2010 debería dar semillas"){
        quinoaReducida.daSemilla().shouldBeTrue()
    }
    describe("Una quinoa que ocupa 0.9mt2 y con origen 2010 no debería dar semillas"){
        quinoaExtendida2010.daSemilla().shouldBeFalse()
    }
    describe("Una quinoa que ocupa 0.9mt2 y con origen 2006 debería dar semillas"){
        quinoaExtendida2006.daSemilla().shouldBeTrue()
    }
    describe("Una soja transgénica nunca da semillas, aunque cumpla la condición general"){
        sojaTransgenica.daSemilla().shouldBeFalse()
    }
    describe("Una peperina debe ocupar el doble de espacio que la menta de mismas características"){
peperina.espacio().shouldBe(mentita.espacio() * 2)
    }
    describe("Una parcela de ancho: 20m y largo: 1m tiene una superficie de 20mts2"){

    }
    describe("Una parcela de ancho: 20m y largo: 1m tolera hasta 4 plantas"){}
    describe("Una parcela de ancho: 1m y largo: 9m tolera hasta 12 plantas"){}
    describe("Una parcela que recibe 10hs de sol tiene complicaciones con una Soja de 0.6mt de altura"){}
    describe("Una parcela que recibe 10hs de sol no tiene problemas con una Soja de 0.8mt de altura"){}
    describe("Una parcela de ancho: 20m y largo: 1m no puede recibir más de 4 plantas"){}

})