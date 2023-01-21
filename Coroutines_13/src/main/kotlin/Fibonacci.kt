import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.yield
import java.math.BigInteger

object Fibonacci {

    suspend fun take(num: Int) {
        withTimeout(3000) {
            try {
                val list = mutableListOf(0.toBigInteger(), 1.toBigInteger())
                for (i in 2..num) {
                    yield()
                    list.add(list[i - 1] + list[i - 2])
                    delay(1)
                }
                println("Порядковый номер $num соответствует числу Фибоначчи ${list[num]}")
                return@withTimeout list[num]
            } catch (e: TimeoutCancellationException) {
                println("Превышено время вычисления")
            }
        }
    }
}