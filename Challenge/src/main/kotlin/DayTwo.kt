package org.example

import java.io.File
import java.io.InputStream
import java.security.KeyStore.TrustedCertificateEntry
import java.util.*

object DayTwo {

    private val cubesMap: Map<String, Int> = mapOf(
        "red" to 12,
        "blue" to 14,
        "green" to 13
    )

    private var cubesMax: Map<String, Int> = mutableMapOf(
        "red" to 0,
        "blue" to 0,
        "green" to 0
    )

    private val sampleGameRecord = listOf(
        "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
        "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
        "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
        "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
        "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
    )

    private var severalStrings: MutableList<String> = mutableListOf()
    init {
        println("**** DAY TWO ****")

        val file = File("/home/andreia/MyOwnProjects/LearningKotlin/Challenge/puzzleGame") //mixString
        val sc = Scanner(file)
        while (sc.hasNextLine()){
            severalStrings.add(sc.nextLine())
        }

    }
    fun sumSets(){
        var arrayStringLine: List<String>
        var powerGame = 0

        severalStrings.forEach{line ->

            println(line)
            arrayStringLine = line.trim().split(":",";")
            var i = 1

            while (i < arrayStringLine.size){

                var gameLine: List<String> = arrayStringLine[i].trim().split(",")
                var index = 0

                while ((index < gameLine.size)){

                    val gameData = gameLine[index].trim().split(" ")
                    saveMaxValue(gameData[1], gameData[0].toInt())

                    index++
                }

                i++
            }

            println("Max of this line is $cubesMax")
            val listAux = cubesMax.values.toList()
            powerGame += listAux[0] * listAux[1] * listAux[2]

            //clear MAX
            cubesMax += mapOf("red" to 0)
            cubesMax += mapOf("blue" to 0)
            cubesMax += mapOf("green" to 0)

        }

        println("Sum of Power Games: $powerGame")
    }

    fun isPossible(){
        var arrayStringLine: List<String>
        var sumIndex = 0

        sampleGameRecord.forEach{line ->

            println(line)
            arrayStringLine = line.trim().split(":",";")
            var isValid = true
            var i = 1

            while (i < arrayStringLine.size && isValid){

                var gameLine: List<String> = arrayStringLine[i].trim().split(",")
                var index = 0

                while ((index < gameLine.size) && isValid){
                    //println("For round: ${index+1}")
                    isValid = isGameValid(gameLine[index])
                    //println("${gameLine[index]} is Valid? $isValid")
                    index++
                }

                i++
            }

            if (isValid) {
                val digit = arrayStringLine[0].replace(Regex("[^0-9]"), "")
                sumIndex = sumIndex + digit.toInt()
            }

        }

        println("Sum of Valid Games: $sumIndex")
    }

    private fun isGameValid(game: String): Boolean{

        val gameData = game.trim().split(" ")

        val index = cubesMap.filterKeys { it == gameData[1] }

        if (index.isNotEmpty()){
            if( index.toList().first().second >= gameData[0].toInt()){
                return true
            }
        }
        return false
    }

    private fun saveMaxValue(cub: String, amount: Int){

        val index = cubesMax.filterKeys { it == cub }

        if (index.isNotEmpty()){
            if( index.toList().first().second <= amount){
                cubesMax += mapOf(cub to amount)
            }
        }

    }

}