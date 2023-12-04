package aoc2022

import utils.println
import utils.readInput

fun main() {
    val (year, day) = "2022" to "Day06"
    fun String.isAllDistinct() = toSet().size == length

    fun findUniqueMarker(s: String, n: Int) = s.windowed(n).indexOfFirst { it.isAllDistinct() } + n
    fun part1(input: List<String>): List<Int> {
        return input.map { findUniqueMarker(it, 4) }
    }

    fun part2(input: List<String>): List<Int> {
        return input.map { findUniqueMarker(it, 14) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(year, "Day04_test")
    check(part1(testInput) == listOf(7, 6, 10, 11))
    check(part2(testInput) == listOf(19, 23, 29, 26))

    val input = readInput(year, day)
    part1(input).println()
    part2(input).println()
}
