package org.example

import java.io.File
import java.util.*

object DayThree {

    private val motorExample = listOf(
        "467..114..",
        "...*......",
        "..35..633.",
        "......#...",
        "617*......",
        ".....+.58.",
        "..592.....",
        "......755.",
        "...$.*....",
        ".664.598.."
    )

    private var severalStrings: MutableList<String> = mutableListOf()
    init {
        println("**** DAY THREE ****")

        val file = File("/home/andreia/MyOwnProjects/LearningKotlin/Challenge/sampleDay3")
        val sc = Scanner(file)
        while (sc.hasNextLine()){
            severalStrings.add(sc.nextLine())
        }
        severalStrings.forEach { println("This is the content of the file: $it") }
    }

    private fun listWithoutPoints ( ): List <String>{

        var result : MutableList<String> = List(severalStrings.size){""}.toMutableList()
        //var result : MutableList<String> = List(motorExample.size){""}.toMutableList()
        var i : Int = 0

        println("Clean Data")
        severalStrings.forEach {
            result[i] = it.split(".").toString()
            result[i] = cleanStructureSymbolsFromString(result[i])
            println(result[i])
            ++i
        }

        return result
    }

    private fun cleanStructureSymbolsFromString (stringToClean : String): String{
        val symbolToRemove = ','
        val symbolToRemove2 = '['
        val symbolToRemove3 = ']'

        var newLine = stringToClean.replace(symbolToRemove.toString(), "")
        newLine = newLine.replace(symbolToRemove2.toString(),"")
        newLine = newLine.replace(symbolToRemove3.toString(), "")

        return newLine

    }

    private fun findFirstIndex(startIndex: Int, line: String):Int{
        var firstIndex = startIndex

        while (firstIndex >= 0 && line[firstIndex].isDigit()) {
            firstIndex -= 1
        }

        return firstIndex
    }

    private fun findLastIndex(startIndex: Int, line: String):Int{
        var lastIndex = startIndex

        while (lastIndex < line.length && line[lastIndex].isDigit()) {
            lastIndex += 1
        }

        return lastIndex
    }

    private fun findPartNumber(line: String, indexSymbol: Int) : Int{

        var total = 0

        if (line[indexSymbol].isDigit()) {
            val firstIndex = findFirstIndex(indexSymbol,line)
            val lastIndex = findLastIndex(indexSymbol,line)

            val numberInLine = line.substring(firstIndex + 1, lastIndex - 1 + 1)
            //take the number to sum
            total += numberInLine.toInt()
            println("The digit in line index-1 is $numberInLine")

        } else {
            if (indexSymbol > 0) {
                if (line[indexSymbol - 1].isDigit()) {
                    val firstIndex = findFirstIndex(indexSymbol-1,line)
                    val numberInLine = line.substring(firstIndex + 1, indexSymbol - 1 + 1)
                    //take the number to sum
                    total += numberInLine.toInt()
                    println("The digit in line index-1 is $numberInLine")
                }
            }

            if (indexSymbol < line.length - 1) {
                if (line[indexSymbol + 1].isDigit()) {
                    val lastIndex = findLastIndex(indexSymbol+1,line)
                    val numberInLine = line.substring(indexSymbol + 1, lastIndex - 1 + 1)
                    //take the number to summ
                    total += numberInLine.toInt()
                    println("The digit in line index +1 is $numberInLine")
                }
            }
        }
        return total
    }


    fun symbolsInList ( ){

        var noPoints = listWithoutPoints()
        var stringWithSymbols: MutableList<String> = List(noPoints.size){""}.toMutableList()
        var totalPartNumbers = 0

        //nao é a noPoints que temos que percorrrer ... mas sim a matriz de symbolos ... tenho que ter uma sem digitos
        noPoints.forEachIndexed { index, element ->
            stringWithSymbols[index] = element.replace(Regex("\\d"), " ")//element.filterNot { it.isDigit() }
        }

        println(stringWithSymbols)


        stringWithSymbols.forEachIndexed {lineIndex, line->
            println("Line: $line")
            line.forEachIndexed { index, element ->
                if (element != ' '){
                    println("Index: $index for the symbol $element")
                    //find part number on the line
                    totalPartNumbers += findPartNumber(noPoints[lineIndex],index)
                    //find part number in previous line
                    if (lineIndex > 0){
                        totalPartNumbers += findPartNumber(noPoints[lineIndex-1], index)
                    }
                    //find part number in next line
                    if (lineIndex < (line.length-1)){
                        totalPartNumbers += findPartNumber(noPoints[lineIndex+1], index)
                    }

                }

            }
            println("The Part number total is: $totalPartNumbers")
        }

    }

}