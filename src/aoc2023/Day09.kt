package aoc2023

import utils.println
import utils.readInput

fun main() {
    val (year, day) = "2023" to "Day09"
    fun part1(input: List<String>): Int {
        val sequences = input.map { it.split(" ").map { n -> n.toInt() } }
        return sequences.sumOf { sequence ->
            val data = mutableListOf(sequence)
            while (data.last().any { it != 0 })
                data += data.last().windowed(2).map { (a, b) -> b - a }
            data.sumOf { it.last() }
        }
    }

    fun part2(input: List<String>): Int {
        val sequences = input.map { it.split(" ").map { n -> n.toInt() } }
        return sequences.sumOf { sequence ->
            val data = mutableListOf(sequence)
            while (data.last().any { it != 0 })
                data += data.last().windowed(2).map { (a, b) -> b - a }
            data.map { it.first() }
                .reversed().reduce { acc, i ->
                    i - acc
                }
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(year, "Day09_test")
    check(part1(testInput) == 114)
    check(part2(testInput) == 2)

    val input = readInput(year, day)
    part1(input).println()
    part2(input).println()
}
