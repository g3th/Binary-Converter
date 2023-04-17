fun clearScreen(){
    ProcessBuilder("clear").redirectOutput(ProcessBuilder.Redirect.INHERIT).start().waitFor()
}

fun isSymbol(string: String) : Boolean{
    val symbols = "'.&<>'`*@\\{}[]^¢« »©†°÷[[ ]]…—–=€!`>≥-\"<≤–×≠#¶( )%π|+±?®§/~™_"
    for (i in symbols){
        for (j in string){
            if(j == i){
               return true
            }
        }
    }
    return false
}
fun checkValue(string: String) : String {
    val valueList = mutableListOf<Int>()
    string.forEach {
        valueList.add( (it.code - '0'.code) )
    }
    for (i in valueList){
        if (i >1){
            clearScreen()
            return "fromDecimalToBinary"
        }
    }
    return "fromBinaryToDecimal"
}

fun binaryToDecConverter(binaryNumber: String) : Long {
    var counter = 0
    val newNum = mutableListOf<Long>()
    val index = binaryNumber.length-1.toLong()
    for (i in index downTo 0) {
        newNum.add((binaryNumber[counter].code - '0'.code) * powerOf(2, i))
        counter += 1
    }
    return newNum.sum()
}

fun decToBinaryConverter(num: Int) : String {
    var convertedNum : Double = num.toDouble()
    var invertedResult = ""
    var finalResult = ""

    do {
        convertedNum /= 2
        if (convertedNum.toString().contains(".5")) {
            invertedResult += "1"
        } else {
            invertedResult += "0"
        }
        convertedNum = convertedNum.toInt().toDouble()
    } while (convertedNum != 0.0)
    for (i in invertedResult.length-1 downTo 0){
        finalResult += (invertedResult[i])
    }
    return finalResult
}

fun powerOf(num1: Long, num2: Long) : Long {
    var counter = 1
    var result = num1
    if (num2 == 0L){
        return 1
    }
    while (counter < num2){
        result *= num1
        counter += 1
    }
    return result
}

fun main(){

    clearScreen()

    var errorFlag = true

    while (errorFlag) {
        
        println("Enter a Binary Number (1s - 0s): ")

        val num = readln()
        val requiredConversion = checkValue(num)

        if (num.all {it.isLetter()}) {
            clearScreen()
            println("Invalid Binary values (letters).")
        } else if (isSymbol(num)){
            clearScreen()
            println("Invalid Binary values (symbols).")
        } else {
            if (requiredConversion == "fromDecimalToBinary") {
                println("\nThe Decimal Number Entered: $num")
                println("Converts Into the Binary Number: ${decToBinaryConverter(num.toInt())}\n")
            } else {
                println("\nThe Binary Number Entered: $num")
                println("Converts Into the Decimal Number: ${binaryToDecConverter(num)}\n")
            }
            errorFlag = false
        }
    }
}
