package aoc2023

import utils.println
import utils.readInput

fun main() {
    val (year, day) = "2023" to "Day01"
    fun part1(input: List<String>): Int {
        val calibrationValues = input.map {str ->
                val numberOnly = str.filter { it.isDigit() }
                if (numberOnly.isNotEmpty()) "${numberOnly.first()}${numberOnly.last()}".toInt() else 0
            }
        return calibrationValues.sum()
    }

    fun part2(input: List<String>): Int {
        val numberMap = mapOf(
            "zero" to "0",
            "one" to "1",
            "two" to "2",
            "three" to "3",
            "four" to "4",
            "five" to "5",
            "six" to "6",
            "seven" to "7",
            "eight" to "8",
            "nine" to "9"
        )
        val pattern = "[0-9]+|[a-z]+${numberMap.keys.joinToString("|")}"
        val patternReverse = "[0-9]+|[a-z]+${numberMap.keys.joinToString("|"){it.reversed()}}"
        val calibrationValues = input.map { str ->
            val firstMatch = Regex(pattern).find(str)?.value ?:0
            val lastMatch = Regex(patternReverse).find(str.reversed())?.value?.reversed() ?:0
            val first = numberMap[firstMatch] ?: firstMatch.toString().first()
            val last = numberMap[lastMatch] ?: lastMatch.toString().last()
            "$first$last".toInt()
        }
        return calibrationValues.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(year, "${day}_test")
    check(part1(testInput) == 22)

    val input = readInput(year, day)
    part1(input).println()
    part2(input).println()
}
