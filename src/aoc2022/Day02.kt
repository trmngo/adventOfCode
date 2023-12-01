package aoc2022

import utils.println
import utils.readInput

fun main() {
    val (year, day) = "2022" to "Day02"
    fun part1(input: List<String>): Int {
//        The below performs like a map because ASCII value math operation
//            'X' -> 1
//            'Y' -> 2
//            'Z' -> 3
        fun shapeScore(shape: Char) = shape - 'X' + 1
        fun outcomeScore(line: String): Int {
            return when (line) {
                "A Z", "B X", "C Y" -> 0
                "A X", "B Y", "C Z" -> 3
                "A Y", "B Z", "C X" -> 6
                else -> error("Check")
            }
        }

        return input.sumOf {round ->
            shapeScore(round[2]) + outcomeScore(round)
        }
    }

    fun part2(input: List<String>): Int {
//        'X' to 0,
//        'Y' to 3,
//        'Z' to 6
        fun shapeScore(shape: Char) = (shape - 'X') * 3
        fun outcomeScore(line: String): Int {
            return when (line) {
                "A Y", "B X", "C Z" -> 1 //Rock
                "A Z", "B Y", "C X" -> 2 //Paper
                "A X", "B Z", "C Y" -> 3 //Scissor
                else -> error("Check")
            }
        }

        return input.sumOf {round ->
            shapeScore(round[2]) + outcomeScore(round)
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(year, "Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput(year, day)
    part1(input).println()
    part2(input).println()
}
