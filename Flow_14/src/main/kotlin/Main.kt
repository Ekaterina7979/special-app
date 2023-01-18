import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() {
    val scope = CoroutineScope(Job() + Dispatchers.Default)
    var player1: Job? = null
    var player2: Job? = null

    runBlocking {

        player1 = launch {
            var counter = 0
            println("The lottery tickets of the player1:")
            val lotteryTicket1 = LotteryTicket().getLotteryTicket()
            val numberForRemove = mutableListOf<Int>()

            Presenter.sharedFlow.collect {
                println(
                    """Motion ${counter + 1}.
                        |Number $it fell out.
                        |Let's check if the numbers match...""".trimMargin()
                )
                counter++
                delay(400)
                checkNumber(list = lotteryTicket1, number = it, namePlayer = "1", numberForRemove3 = numberForRemove)
                if (numberForRemove.size == 15) {
                    println("Game over. The player 1 won")
                    player2?.cancel()
                    cancel()
                }
            }
        }
        println("The player 1 start playing...")

        player2 = launch {
            var counter = 0
            println("The lottery tickets of the player2:")
            val lotteryTicket2 = LotteryTicket().getLotteryTicket()
            val numberForRemove = mutableListOf<Int>()

            Presenter.sharedFlow.collect {
                println(
                )
                counter++
                """Motion ${counter + 1}.
                        |Number $it fell out.
                        |Let's check if the numbers match...""".trimMargin()
                delay(400)
                checkNumber(list = lotteryTicket2, number = it, namePlayer = "2", numberForRemove3 = numberForRemove)
                if (numberForRemove.size == 15) {
                    println("Game over. The player 2 won")
                    player1?.cancel()
                    cancel()
                }
            }
        }
        println("The player 2 start playing...")
    }
}

fun checkNumber(list: Array<Array<Int>>, number: Int, namePlayer: String, numberForRemove3: MutableList<Int>) {

    list.forEach { row ->
        row.forEach { item ->
            if (item != 0 && item == number) {
                println(
                    """The number you are looking for is found
                                    | in the lottery ticket of the player $namePlayer""".trimMargin()
                )
                numberForRemove3.add(item)
            }
        }
    }
}

// этот комментарий я пишу для тренировки работы с git

object Presenter {
    private val scope = CoroutineScope(Job() + Dispatchers.Default)
    private val sharedFlow1 = MutableSharedFlow<Int>()
    val sharedFlow = sharedFlow1.asSharedFlow()
    init {
        scope.launch {
            val kegs: MutableList<Int> = (1..90).toMutableList()
            kegs.shuffle()
            for (it in 0 until kegs.size) {
                sharedFlow1.emit(kegs[it])
                delay(1000)
            }
            scope.cancel()
        }
    }
}

