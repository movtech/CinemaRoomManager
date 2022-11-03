package cinema
    var ticketPrice: Int = 0

    const val NUMBER_1 = 1
    const val NUMBER_2 = 2
    const val NUMBER_3 = 3
    const val NUMBER_0 = 0

    val allRows: MutableList<MutableList<Char>>  = mutableListOf()

    var numberOfSeatSoldCounter = 0
    var currentIncome = 0
    var totalIncome = 0


fun main(args: Array<String>) {

    println("Enter the number of rows:")
    val numberOfRows = readLine()!!.toInt()

    println("Enter the number of seats in each row:")
    val numberOfSeatInEachRow = readLine()!!.toInt()
    val totalNumberOfSeat = numberOfRows * numberOfSeatInEachRow

    // var profit: Int

    if (totalNumberOfSeat <= 60) {
        // Screen Room
        totalIncome = totalNumberOfSeat * 10
    } else {
        // Larger Room
        if(numberOfRows % 2 == 0) {
            totalIncome = ((numberOfRows / 2) * numberOfSeatInEachRow * 10) + ((totalNumberOfSeat / 2) * 8)
        } else {
            totalIncome = ((numberOfRows / 2) * numberOfSeatInEachRow * 10) + (((numberOfRows / 2) + 1) * numberOfSeatInEachRow * 8)
        }
    }

    //println()

    //println("Total Income:\n$$profit")


    // Adding available seats to all rows
    for (j in 1..numberOfRows) {
        val row: MutableList<Char>  = mutableListOf()

        for (k in 1..numberOfSeatInEachRow) {
            row.add('S')
        }
        allRows.add(row)
    }

    println()

    do{
        // Print Menu
        printMenu()
        var selectedMenuItem = readLine()!!.toInt()
        when (selectedMenuItem) {
            NUMBER_1 -> showSeats(numberOfRows, numberOfSeatInEachRow)
            NUMBER_2 -> buyTicket(totalNumberOfSeat, numberOfRows)
            NUMBER_3 -> showStatistics(totalNumberOfSeat)
            NUMBER_0 -> selectedMenuItem = 0
            else -> println("Unknown selection")
        }
    }while(selectedMenuItem != 0)

    println("You have successfully exited the program!")
}

fun buyTicket(totalNumberOfSeat: Int, numberOfRows: Int) {
    println()

    println("Enter a row number:")
    val rowNumber = readLine()!!.toInt()

    println("Enter a seat number in that row:")
    val seatNumberInRow = readLine()!!.toInt()

    try {
        if (allRows[rowNumber - 1][seatNumberInRow - 1] == 'S') {
            // Calculate ticket price
            calculateTicketPrice(totalNumberOfSeat, rowNumber, numberOfRows)

            // Seat sold
            allRows[rowNumber - 1][seatNumberInRow - 1] = 'B'

            numberOfSeatSoldCounter++
        } else {
            println()
            println("That ticket has already been purchased!")
            buyTicket(totalNumberOfSeat, numberOfRows)
        }
    } catch (e: IndexOutOfBoundsException) {
        println()
        println("Wrong input!")
        buyTicket(totalNumberOfSeat, numberOfRows)
    }
}

fun calculateTicketPrice(totalNumberOfSeat: Int, rowNumber: Int, numberOfRows: Int) {
    if (totalNumberOfSeat <= 60) {
        // Screen Room
        ticketPrice = 10
    } else {
        // Larger Room
        if (rowNumber <= (numberOfRows / 2)) ticketPrice = 10 else ticketPrice = 8
    }

    currentIncome += ticketPrice

    println()

    println("Ticket price: $$ticketPrice")

    println()
}

fun printMenu() = println(
        """
            1. Show the seats
            2. Buy a ticket
            3. Statistics
            0. Exit
        """.trimIndent()
)

fun showSeats(mNumberOfRows: Int, mNumberOfSeatInEachRow: Int) {
    println()
    print("Cinema:\n  ")
    for (i in 1..mNumberOfSeatInEachRow) {
        print(i)
        print(' ')
    }

    println()

    for (j in 1..mNumberOfRows) {
        val row: MutableList<Char>  = mutableListOf()
        print(j)
        print(' ')

        for (k in 1..mNumberOfSeatInEachRow) {
            print(cinema.allRows[j - 1][k - 1])
            print(' ')
        }
        cinema.allRows.add(row)
        println()
    }

    println()
}

fun showStatistics(totalNumberOfSeat: Int) {
    val percentage = numberOfSeatSoldCounter.toDouble() / totalNumberOfSeat.toDouble() * 100
    val formattedPercentage = String.format("%.2f", percentage)

    println()

    println("Number of purchased tickets: $numberOfSeatSoldCounter")
    println("Percentage: $formattedPercentage%")
    println("Current income: $$currentIncome")
    println("Total income: $$totalIncome")

    println()
}