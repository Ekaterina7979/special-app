import kotlinx.coroutines.*
import java.math.BigInteger

val scope = CoroutineScope(Dispatchers.Default)

fun main() {

    // runBlocking {
    //launch { Fibonacci.take(50) }
    //launch { Fibonacci.take(100) }
    //launch { Fibonacci.take(155) }
    //launch { Fibonacci.take(300) }
    //launch { Fibonacci.take(200) }
    //launch { Fibonacci.take(160) }
    //}

    runFib(300, 100, 50)
}

fun runFib(n1: Int, n2: Int, n3: Int) {
    runBlocking {
        scope.launch {
            for (i in 1..100) {
                delay(5)
                if (i % 100 == 0)
                    println(".")
                else print(".")
            }
        }
        val job = scope
        scope.launch {
            val fib = Fibonacci.take(n1)
        }
        scope.launch {
            val fib = Fibonacci.take(n2)
        }
        scope.launch {
            val fib = Fibonacci.take(n3)
        }
        scope.launch {
            delay(4000)
            println("Время вышло. Все операции отменяются")
            job.cancel()
        }
        scope.coroutineContext.job.join()
    }
}
