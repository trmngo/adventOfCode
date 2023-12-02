package aoc2023

import utils.println
import utils.readInput

enum class StartFrom {
    BEGINNING,
    LAST
}
fun main() {
    val (year, day) = "2023" to "Day01"
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
    fun part1(input: List<String>): Int {
        val calibrationValues = input.map { str ->
            val numberOnly = str.filter { it.isDigit() }
            if (numberOnly.isNotEmpty()) "${numberOnly.first()}${numberOnly.last()}".toInt() else 0
        }
        return calibrationValues.sum()
    }

    fun part2(input: List<String>): Int {
        val pattern = "[0-9]+|[a-z]+${numberMap.keys.joinToString("|")}"
        val patternReverse = "[0-9]+|[a-z]+${numberMap.keys.joinToString("|") { it.reversed() }}"
        return input.sumOf { str ->
            val firstMatch = Regex(pattern).find(str)?.value ?: 0
            val lastMatch = Regex(patternReverse).find(str.reversed())?.value?.reversed() ?: 0
            val first = numberMap[firstMatch] ?: firstMatch.toString().first()
            val last = numberMap[lastMatch] ?: lastMatch.toString().last()
            "$first$last".toInt()
        }
    }

    fun getNumber(line:String, startFrom: StartFrom) : Int {
        val (wordIdx, word) = when (startFrom) {
            StartFrom.BEGINNING -> line.findAnyOf(numberMap.keys) ?: (Int.MAX_VALUE to "")
            StartFrom.LAST -> line.findLastAnyOf(numberMap.keys) ?: (Int.MIN_VALUE to "")
        }
        val (digitIdx, digit) = when (startFrom) {
            StartFrom.BEGINNING -> line.findAnyOf(numberMap.values) ?: (Int.MAX_VALUE to "")
            StartFrom.LAST -> line.findLastAnyOf(numberMap.values) ?: (Int.MIN_VALUE to "")
        }
        val shouldPickDigit = when (startFrom) {
            StartFrom.BEGINNING -> wordIdx > digitIdx
            StartFrom.LAST -> wordIdx < digitIdx
        }
        return if (shouldPickDigit) {
            digit.toInt()
        } else {
            numberMap[word]!!.toInt()
        }
    }

    fun part2Restructure(input: List<String>): Int {
        return input.sumOf {
            getNumber(it, StartFrom.BEGINNING) * 10 + getNumber(it, StartFrom.LAST)
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(year, "${day}_test")
    check(part1(testInput) == 22)

    val input = readInput(year, day)
    part1(input).println()
    part2(input).println()
    part2Restructure(input).println()
}
