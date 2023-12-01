package aoc2022

import utils.println
import utils.readInput

fun main() {
    val (year, day) = "2022" to "Day03"
    fun Char.priority() = if (this.isLowerCase()) (this - 'a') + 1 else (this - 'A') + 27
    fun List<String>.sharedItem(): Char = map { it.toSet() }
        .reduce{ left, right -> left.intersect(right) }
        .first()
    fun part1(input: List<String>): Int {
        val rucksacks = input.map { it.chunked(it.length / 2) }
        val commonItems = rucksacks.map { it.sharedItem() }
        return commonItems.sumOf { it.priority() }
    }

    //region Part 2
    fun part2(input: List<String>): Int {
        val rucksacks = input.chunked(3)
        val commonItems = rucksacks.map {it.sharedItem()}
        return commonItems.sumOf { it.priority() }
    }



    // test if implementation meets criteria from the description, like:
    val testInput = readInput(year, "Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)
    //endregion


    val input = readInput(year, day)
    part1(input).println()
    part2(input).println()
}
