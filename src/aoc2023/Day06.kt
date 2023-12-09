package aoc2023

import utils.println
import utils.readInput

fun main() {
    val (year, day) = "2023" to "Day06"
    val whiteSpace = Regex("\\s+")
    fun part1(input: List<String>): Int {
        val times = input[0].removePrefix("Time:").trim().split(whiteSpace).map { it.toInt() }
        val distances = input[1].removePrefix("Distance:").trim().split(whiteSpace).map { it.toInt() }
        return times.foldIndexed(1) { idx, res, time ->
            val dist = distances[idx]
            res * (1..time).count { n ->
                (time - n) * n > dist
            }
        }
    }

    fun part2(input: List<String>): Int {
        val time = input[0].removePrefix("Time:").replace(whiteSpace, "").toLong()
        val distance = input[1].removePrefix("Distance:").replace(whiteSpace,"").toLong()
        return (1..time).count { n ->
            (time - n) * n > distance
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(year, "Day06_test")
    check(part1(testInput) == 288)
    check(part2(testInput) == 71503)

    val input = readInput(year, day)
    part1(input).println()
    part2(input).println()
}
