package aoc2023

import utils.println
import utils.readInput
import kotlin.math.pow

data class Card(val number: Int, val winning: Set<Int>, val have: Set<Int>) {
    fun matches(): Int {
        return winning.intersect(have).size
    }

    fun value(): Int {
        return 2.0.pow(matches() - 1.0).toInt()
    }


    companion object {
        private val cardRegex = Regex("""Card\s+(\d+):\s+([^|]*)\s+\|\s+(.*)""")

        fun fromLine(line: String): Card {
            val parts =
                cardRegex.matchEntire(line)?.groupValues ?: throw IllegalArgumentException("Invalid line: $line")
            val num = parts[1].toInt()
            val winning = parts[2].split("  ", " ").map { it.toInt() }.toSet()
            val have = parts[3].split("  ", " ").map { it.toInt() }.toSet()
            return Card(num, winning, have)
        }
    }
}
fun main() {
    val (year, day) = "2023" to "Day04"

    fun part1(input: List<String>): Int {
        return input.sumOf {
            Card.fromLine(it).value()
        }
    }

    fun part2(input: List<String>): Int {
        data class CardWithCount(val card: Card, var count: Int) {
            fun value(): Int {
                return card.matches()
            }

            fun addCopies(num: Int) {
                count += num
            }
        }
        val cards = input.map { Card.fromLine(it) }.map {
            CardWithCount(it, 1)
        }
        for ((i, card) in cards.withIndex()) {
            val value = card.value()
            for (j in 1..value) {
                cards[i + j].addCopies(card.count)
            }
        }
        return cards.sumOf { it.count }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(year, "Day04_test")
    check(part1(testInput) == 13)

    val input = readInput(year, day)
    part1(input).println()
    part2(input).println()
}
