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
            println("Invalid Binary values.")
            return true
        }
    }
    return false
}

fun powerOf(num1: Int, num2: Int) : Int {
    var counter = 1
    var result = num1
    if (num2 == 0){
        return 1
    }
    while (counter < num2){
        result = result * num1
        counter += 1
    }
    return result
}
fun main(){

    var errorFlag = true
    var num = ""

    while (errorFlag) {
        println("Enter Binary Number: ")
        num = readln()
        if (num.all { it.isLetter()}){
            clearScreen()
            println("Invalid Binary values.")
        } else if (!invalidValue(num)){
            errorFlag = false
        }
    }

    var counter = 0
    val newNum = mutableListOf<Int>()
    val index = num.length-1
    for (i in index downTo 0){
        newNum.add( (num[counter].code - '0'.code) * powerOf(2, i))
        counter += 1
    }
    println(newNum.sum())
}
