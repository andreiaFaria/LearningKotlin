package org.example

import java.io.File
import java.util.Scanner

enum class NUMBER{
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE
}
object DayOne {
    private val sampleSeveralStringsHardCoded: List<String> = listOf(
        "1abc2",
        "pqr3stu8vwx",
        "a1b2c3d4e5f",
        "treb7uchet"
    )
    var sampleMixStringLettersNumbers: MutableList<String> = mutableListOf(
        "two1nine",
        "eightwothree",
        "abcone2threexyz",
        "xtwone3four",
        "4nineeightseven2",
        "zoneight234",
        "7pqrstsixteen"
    )


    private var severalStrings: MutableList<String> = mutableListOf()
    init {
        println("**** DAY ONE ****")

        val file = File("/home/andreia/MyOwnProjects/LearningKotlin/Challenge/puzzleInput") //mixString
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

    fun challengeV5() {
        println("The Sum of the file is: ${severalStrings.sum2()}")

    }

    private fun MutableList<String>.sum2(): Int{
        val severalInt = this.map { value -> findSumNumberInText(value) }

        val finalSum = severalInt.fold(0) { sum, value ->
            sum + value
        }
        return finalSum
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
    private fun findNumbers(mixString: String): MutableMap<Int, String> {
        val textedNumbers: MutableMap<Int, String> = mutableMapOf()
        var i = 0
        mixString.forEach { value ->
            if (value.isDigit()){
                textedNumbers += Pair(i,value.toString())
            }
            i++
        }
        return textedNumbers
    }

    private fun findSumNumberInText(mixStringLettersNumbers: String): Int{

        val textedNumbers: MutableMap<Int, String> = mutableMapOf()
        var sum = ""

        //procura numeros escritos e adiciona em textedNumbers
        enumValues<NUMBER>().forEach {
            var index = mixStringLettersNumbers.indexOf(it.name, ignoreCase=true)

            while (index < mixStringLettersNumbers.length && index != -1){
                println("There is ${it.name} in index: ${index}")
                textedNumbers += Pair(index,(it.ordinal+1).toString())

                index = mixStringLettersNumbers.indexOf(it.name, ignoreCase=true, startIndex = index+1)
            }
        }

        //procura numeros digitos e adiciona em textedNumbers
        textedNumbers += findNumbers(mixStringLettersNumbers)

        println(mixStringLettersNumbers)
        if (textedNumbers.isNotEmpty()){

            if (textedNumbers.size >= 2){
                val listNumbers = textedNumbers.toList().sortedBy { it.first }

                val firstNumber = listNumbers.first().second
                val secondNumber = listNumbers.last().second

                sum = firstNumber+secondNumber

                println("first: $firstNumber and second: $secondNumber and the sum is $sum")
            }else {
                val firstNumber = textedNumbers.toList().first().second
                sum = firstNumber + firstNumber
                println("first: $firstNumber and second is the first: $firstNumber and the sum is $sum")
            }

        }

        return sum.toInt()
    }

    private fun wasAlreadyFoundThisNumber(textedNumbers: MutableMap<Int, String>, number: String): Int{
        val filteredMap = textedNumbers.filterValues{it == number}
        return if (filteredMap.isNotEmpty()) 1
        else 0
    }

}