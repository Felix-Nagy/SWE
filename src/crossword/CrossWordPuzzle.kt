package crossword

import kotlin.random.Random

enum class Direction {
    HORIZONTAL, VERTICAL
}

class WordPosition(var x: Int, var y: Int, var dir: Direction) {
    override fun toString(): String {
        return "($x | $y | $dir)"
    }
    //you can do val (x, y) = wordPos
    operator fun component1(): Int {
        return x
    }

    operator fun component2(): Int {
        return y
    }
}

class QuestionAnswerPair(val question: String, val answer: String) {
    override fun toString(): String {
        return "($question? $answer)"
    }
}

class BoardEntry(val questionAnswerPair: QuestionAnswerPair, val wordPos: WordPosition) {
    override fun toString(): String {
        return "($questionAnswerPair - $wordPos)"
    }
}

fun abs(x: Int): Int = if (x < 0) -x else x

/** the boards dimensions. the board is square so only one needed */
private const val BOARD_DIMENSION = 8
/** the character used to signify that an entry in the board array is empty, looks like this: >> · << */
private const val EMPTY_FIELD = '\u00B7'

class CrossWordPuzzle(private val questionAnswerPairs: Array<QuestionAnswerPair>) {
    /**
     * A two-dimensional grid representing the crossword puzzle.
     * Its size is [BOARD_DIMENSION]x[BOARD_DIMENSION] and is initially filled with [EMPTY_FIELD].
     * Words that are to be added in [genBoard] are put in here using [insertWord].
     */
    val board: Array<CharArray> = Array(BOARD_DIMENSION) { CharArray(BOARD_DIMENSION) { EMPTY_FIELD } }
    /**
     * all entries that are added to the [board] are kept track of here,
     * including the word, its word position and direction as well as its corresponding question.
     */
    private val boardEntries: ArrayList<BoardEntry> = ArrayList()

    init {
        genBoard()
    }

    private fun genBoard() {
        for (qap in questionAnswerPairs) {
            val wordPosition = getFreeSpace(qap.answer) ?: continue
            insertWord(qap.answer, wordPosition)
            boardEntries.add(BoardEntry(qap, wordPosition))
        }
        /*println(boardEntries)
        printBoard(this.board)
        printQuestionBoard()*/
    }

    /**
     * Tries to find a free space on the board to fit the given word.
     * @param word the string that's supposed to go on the word
     * @return if a position is found, the position and direction is returned.
     * if none is found, return null
     */
    private fun getFreeSpace(word: String): WordPosition? {
        for (x in 0 until BOARD_DIMENSION) {
            for (y in 0 until BOARD_DIMENSION) {
                for (dir in Direction.values()) {
                    val random = Random.nextInt(BOARD_DIMENSION)
                    val offsetX = abs((x + random) % BOARD_DIMENSION) //a little bit of randomisation ..
                    val offsetY = abs((y + random) % BOARD_DIMENSION)
                    if (!isObstructed(word, offsetX, offsetY, dir)) {
                        return WordPosition(offsetX, offsetY, dir)
                    }
                }
            }
        }

        return null
    }

    /**
     * @param word the [QuestionAnswerPair.answer] we want to put on the board
     * @param x x-coord where we want to put the word start
     * @param y y-coord where we want to put the word start
     * @param dir [Direction] we want the word to go
     * @return (true|false) whether the word fits in the board with the given coordinates and direction
     *
     * the word is obstructed if
     *  - it goes out of bounds
     *  - another word crosses it, however it may cross same letters
     */
    private fun isObstructed(word: String, x: Int, y: Int, dir: Direction): Boolean {
        val wordIter = word.iterator()
        if (dir == Direction.HORIZONTAL) {
            if (y + word.length > BOARD_DIMENSION)
                return true

            for ((index, _) in wordIter.withIndex()) {
                if (board[x][y + index] != EMPTY_FIELD) {
                    if (board[x][y + index] != word[index]) {
                        return true
                    }
                }
            }
        }

        if (dir == Direction.VERTICAL) {
            if (x + word.length > BOARD_DIMENSION)
                return true

            for ((index, _) in wordIter.withIndex()) {
                if (board[x + index][y] != EMPTY_FIELD) {
                    if (board[x + index][y] != word[index]) {
                        return true
                    }
                }
            }
        }

        return false
    }

    /**
     * writes a word in [board], beginning at the given position and going into the given direction
     * @param word word to be inserted
     * @param wordPos contains information about the coordinates and direction
     */
    private fun insertWord(word: String, wordPos: WordPosition) {
        val wordIter = word.iterator()
        if (wordPos.dir == Direction.HORIZONTAL) {
            for ((index, c) in wordIter.withIndex()) {
                this.board[wordPos.x][wordPos.y + index] = c
            }
        }
        
        if (wordPos.dir == Direction.VERTICAL) {
            for ((index, c) in wordIter.withIndex()) {
                this.board[wordPos.x + index][wordPos.y] = c
            }
        }
    }

    fun printBoard(board: Array<CharArray>) {
        for (boardRow in board) {
            for (c in boardRow) {
                print("$c\t")
            }
            println()
        }
        println()
    }

    fun printQuestionBoard() {
        val questionPosBoard: Array<CharArray> = Array(BOARD_DIMENSION) { CharArray(BOARD_DIMENSION) { EMPTY_FIELD } }
        for ((index, entry) in this.boardEntries.iterator().withIndex()) {
            val (x, y) = entry.wordPos
            questionPosBoard[x][y] = index.toString()[0] //int to char
        }

        printBoard(questionPosBoard)

        for ((index, entry) in this.boardEntries.iterator().withIndex()) {
            println("$index\t${entry.wordPos.dir.toString().toLowerCase()}\t${entry.questionAnswerPair.question}")
        }
    }

    fun questionBoardToString(): String {
        var output = ""
        val questionPosBoard: Array<CharArray> = Array(BOARD_DIMENSION) { CharArray(BOARD_DIMENSION) { EMPTY_FIELD } }
        for ((index, entry) in this.boardEntries.iterator().withIndex()) {
            val (x, y) = entry.wordPos
            questionPosBoard[x][y] = index.toString()[0] //int to char
        }

        for (boardRow in questionPosBoard) {
            for (c in boardRow) {
                output += "$c\t"
            }
            output += "\n"
        }

        return output
    }
}