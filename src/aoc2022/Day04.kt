package aoc2022

import utils.println
import utils.readInput

fun main() {
    val (year, day) = "2022" to "Day04"
    fun parse(input: List<String>) = input.map { line ->
            val (elf1Start, elf1End) = line.substringBefore(",").split("-").map { it.toInt() }
            val (elf2Start, elf2End) = line.substringAfter(",").split("-").map { it.toInt() }
            (elf1Start..elf1End) to (elf2Start..elf2End)
        }
    operator fun IntRange.contains(other: IntRange) = other.first in this && other.last in this
    fun part1(input: List<String>): Int {
        return parse(input)
            .count { (elf1, elf2) ->
                elf1 in elf2 || elf2 in elf1
            }
    }

    fun part2(input: List<String>): Int {
        return parse(input).count { (elf1, elf2) ->
            elf1.intersect(elf2).isNotEmpty()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(year, "Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput(year, day)
    part1(input).println()
    part2(input).println()
}
