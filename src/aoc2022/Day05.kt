package aoc2022

import utils.println
import utils.readInput

fun main() {
    val (year, day) = "2022" to "Day05"
    data class Move(val qty: Int, val from: Int, val to: Int)
    fun createStack(input: List<String>): List<MutableList<Char>> {
        val stackRows = input.takeWhile { it.contains("[") }
        return (1..stackRows.last().length step 4).map { i ->
            stackRows.mapNotNull { it.getOrNull(i) }
                .filter { it.isUpperCase() }
                .toMutableList()
        }
    }
    fun readMoves(input: List<String>): List<Move> {
        return input.filter { it.startsWith("move") }
            .map { move ->
                move.split(" ").let {
                    Move(it[1].toInt(), it[3].toInt() -1, it[5].toInt() -1)
                }
            }
    }

    fun readMovesWithRegex(input: List<String>): List<Move> {
        val pattern = """move (\d+) from (\d+) to (\d+)"""
        return input.filter { it.startsWith("move") }
            .map {
                val (qty, from, to) = Regex(pattern).matchEntire(it)!!.destructured
                Move(qty.toInt(), from.toInt() - 1, to.toInt() - 1)
            }
    }
    fun performMoves(stacks: List<MutableList<Char>>, moves: List<Move>, reverse: Boolean) {
        moves.forEach { move ->
            val toBeMoved = stacks[move.from].take(move.qty)
            repeat(move.qty) { stacks[move.from].removeFirst() }
            stacks[move.to].addAll(0, if (reverse) toBeMoved.reversed() else toBeMoved)
        }
    }
    fun part1(input: List<String>): String {
        val stacks: List<MutableList<Char>> = createStack(input)
        val moves: List<Move> = readMovesWithRegex(input)
        performMoves(stacks, moves, true)
        return stacks.map { it.first() }.joinToString("")
    }

    fun part2(input: List<String>): String {
        val stacks: List<MutableList<Char>> = createStack(input)
        val moves: List<Move> = readMoves(input)
        performMoves(stacks, moves, false)
        return stacks.map { it.first() }.joinToString("")
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(year, "Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput(year, day)
    part1(input).println()
    part2(input).println()
}
