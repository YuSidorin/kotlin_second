fun main() {

// first one
    val seconds = 61
    var agoToTextString = agoToText(seconds)

// second one
    val cardType = "Maestro"
    val monthAmount = 50000
    val currentAmount = 5000
    calculateDiscount(cardType, monthAmount, currentAmount)
}

private fun agoToText(seconds: Int): String {
    var str = "был(а) "
    var minutesWord = getMinutesWord(seconds)
    var hourWord = getHoursWord(seconds)
    when (seconds) {
        in 1..60 -> str += "только что"
        in 61..60 * 60 -> str += "в сети  ${seconds / 60} $minutesWord назад"
        in 361..24 * 360 -> str += "в сети  ${seconds / 360} $hourWord назад"
        in 24 * 361..24 * 60 * 60 * 2 -> str += "в сети сегодня"
        in 24 * 60 * 60 * 2 + 1..24 * 60 * 60 * 3 -> str += "в сети вчера"
        else -> str += "в сети давно"
    }
    println(str)
    return str
}

private fun getMinutesWord(seconds: Int): String {
    var string = "минут"
    var minutes = seconds / 60
    if (minutes == 1 || minutes % 10 === 1) {
        string += "у"
    } else if ((minutes == 2 || minutes == 3 || minutes == 4 ||
                minutes % 10 == 2 || minutes % 10 == 3 || minutes % 10 == 4)
        && minutes !== 12 && minutes !== 13 && minutes !== 14
    ) {
        string += "ы"
    }
    return string
}

private fun getHoursWord(seconds: Int): String {
    var string = "час"
    var hours = seconds / 60
    if (hours > 5 && hours < 21) {
        string += "ов"
    } else if (hours > 1 && hours < 5) {
        string += "а"
    }
    return string
}

private fun calculateDiscount(cardType: String = "VK Pay", monthAmount: Int, currentAmount: Int): Int {
    val dailyLimit = 150_000
    val monthlyLimit = 600_000
    val maestroMinLimit = 300
    val maestroMaxLimit = 75000
    var totalResult = 0

    if (currentAmount < dailyLimit && monthAmount < monthlyLimit) {
        when (cardType) {
            "VK Pay" -> totalResult = currentAmount
            "Mastercard", "Maestro" -> if (monthAmount > maestroMinLimit && monthAmount < maestroMaxLimit) totalResult =
                currentAmount
            else totalResult = currentAmount - currentAmount / 100 * 6 - 200
            "Visa", "Мир" -> totalResult =
                currentAmount - if (currentAmount / 100 * 75 > 350) currentAmount / 100 * 75 else 350

        }
    }
    println(totalResult)
    return totalResult

}