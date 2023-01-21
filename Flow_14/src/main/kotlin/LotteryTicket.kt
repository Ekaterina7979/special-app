import java.util.Collections.shuffle


class LotteryTicket {

    private val lotteryTicket = Array(3) { Array(9) { 0 } }

    fun getLotteryTicket(): Array<Array<Int>> {
        val numbersList1: MutableList<Int> = (1..90).toMutableList()
        shuffle(numbersList1)
        val listNull = listOf(0, 0, 0, 0)
        val row1 = numbersList1.subList(0, 5)
        val rowList1 = row1 + listNull
        shuffle(rowList1)
        val row2 = numbersList1.subList(6, 11)
        val rowList2 = row2 + listNull
        shuffle(rowList2)
        val row3 = numbersList1.subList(12, 17)
        val rowList3 = row3 + listNull
        shuffle(rowList3)
        for (i in lotteryTicket.indices) {
            for (t in 0 until 9) {
                lotteryTicket[0] = rowList1.toTypedArray()
                lotteryTicket[1] = rowList2.toTypedArray()
                lotteryTicket[2] = rowList3.toTypedArray()
            }
        }
        for (row in lotteryTicket) {
            for (cell in row) {
                print("|$cell\t")
            }
            println("|")
        }
        println("=====================================")
        return this.lotteryTicket
    }
}