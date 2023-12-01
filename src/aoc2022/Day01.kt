package aoc2022

import utils.println
import java.io.File

fun main() {
    val (year, day) = "2022" to "Day01"
    fun parseInput(input: String) = input.split("\n\n").map { elf ->
        elf.lines().sumOf { it.toInt() }
    }

    fun part1(input: String): Int {
        val data = parseInput(input)
        return data.max()
    }

    fun part2(input: String): Int {
        val data = parseInput(input)
        return data.sortedDescending().take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = File("src/main/resources/aoc2022/Day01_test.txt").readText()
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = File("src/main/resources/aoc2022/Day01.txt").readText()
    part1(input).println()
    part2(input).println()
}
