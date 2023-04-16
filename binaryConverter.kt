fun clearScreen(){
    ProcessBuilder("clear").redirectOutput(ProcessBuilder.Redirect.INHERIT).start().waitFor()
}

fun invalidValue(string: String) : Boolean {
    val valueList = mutableListOf<Int>()
    string.forEach {
        valueList.add( (it.code - '0'.code) )
    }
    for (i in valueList){
        if (i >1){
            clearScreen()
            println("Invalid Binary values (contains numbers larger than 1s or 0s).")
            return true
        }
    }
    return false
}

fun powerOf(num1: Long, num2: Long) : Long {
    var counter = 1
    var result = num1
    if (num2 == 0L){
        return 1
    }
    while (counter < num2){
        result = result * num1
        counter += 1
    }
    return result
}
fun main(){
	clearScreen()
    var errorFlag = true
    var num = ""

    while (errorFlag) {
        println("Enter a Binary Number (1s - 0s): ")
        num = readln()
        if (num.all { it.isLetter()}){
            clearScreen()
            println("Invalid Binary values (letters).")
        } else if (!invalidValue(num)){
            errorFlag = false
        }
    }

    var counter = 0
    val newNum = mutableListOf<Long>()
    val index = num.length-1.toLong()
    for (i in index downTo 0){
        newNum.add( (num[counter].code - '0'.code) * powerOf(2, i))
        counter += 1
    }
    println("\nThe Binary Number Entered: $num")
    println("Converts Into the Decimal Number: ${newNum.sum()}\n")
}
