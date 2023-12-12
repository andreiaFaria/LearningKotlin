package org.example

import java.io.File
import java.util.Scanner

object DayOne {
    private val severalStringsHardCoded: List<String> = listOf(
        "1abc2",
        "pqr3stu8vwx",
        "a1b2c3d4e5f",
        "treb7uchet"
    )
    private var severalStrings: MutableList<String> = mutableListOf()
    init {
        println("**** DAY ONE ****")

        val file = File("/home/andreia/MyOwnProjects/LearningKotlin/Challenge/mixString")
        val sc = Scanner(file)
        while (sc.hasNextLine()){
            severalStrings.add(sc.nextLine())
        }
        severalStrings.forEach { println("This is the content of the file: $it") }
    }

    fun challenge() {
        var finalSum = 0
        println("New Challenge")
        severalStrings.forEach { value ->
            finalSum += findFirstLastNumber(value)
        }
        println("Final SUM = $finalSum")

    }
    //««««««««««««««««««««««« Version 2 »»»»»»»»»»»»»»»»»»»»»»»»»»»»
    fun challengeV2() {

        println("New Challenge IMPROVED")
        val finalSum = severalStrings.fold(0) { sum, value ->
            sum + findFirstLastNumberV2(value)
        }
        println("Final SUM = $finalSum")

    }
    //««««««««««««««««««««««« Version 3 »»»»»»»»»»»»»»»»»»»»»»»»»»»»
    fun challengeV3() {

        val severalInt = severalStrings.map { value -> findFirstLastNumberV2(value) }

        println("New Challenge IMPROVED")
        val finalSum = severalInt.fold(0) { sum, value ->
            sum + value
        }
        println("Final SUM = $finalSum")

    }
    fun challengeV4() {
        println("The Sum of the file is: ${severalStrings.sum()}")
    }


    private fun MutableList<String>.sum(): Int{
        val severalInt = this.map { value -> findFirstLastNumberV2(value) }

        val finalSum = severalInt.fold(0) { sum, value ->
            sum + value
        }
        return finalSum
    }

    private fun findFirstLastNumber(mixString: String): Int{
        var valuesInt = ""

        mixString.forEach { value ->
            if (value.isDigit()) valuesInt += value
        }

        val valueFinal: String = valuesInt[0].toString() + valuesInt[valuesInt.length-1].toString()
        println("The first and last number of $mixString is: $valueFinal")

        return  valueFinal.toInt()
    }

    private fun findFirstLastNumberV2(mixString: String): Int{

        val valueFinal: String = findFirstNumber(mixString) + findLastNumber(mixString)
        println("The first and last number of $mixString is: $valueFinal")

        return  valueFinal.toInt()
    }

    private fun findFirstNumber(mixString: String): String {

        mixString.forEach { value ->
            if (value.isDigit()) return value.toString()
        }
        return ""
    }

    private fun findLastNumber(mixString: String): String {

        for (i in mixString.length-1 downTo 0){ //i in mixString.length-1 downTo 0 step 1 »» posso omitir o step
            if(mixString[i].isDigit()){
                return mixString[i].toString()
            }
        }
        return ""
    }

}