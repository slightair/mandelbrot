val Width = 129
val Height = 48
val MaxTrial = 200

def recurrence(pxn: Double, pyn: Double, a: Double, b: Double, trial:Int):Int = trial match {
    case MaxTrial => MaxTrial
    case _ => {
        val xn = pxn * pxn - pyn * pyn + a
        val yn = 2 * pxn * pyn + b
            
        (xn * xn + yn * yn) match {
            case z if z > 4.0 => trial
            case _ => recurrence(xn, yn, a, b, trial + 1)
        }
    }
}

def checkDivergence(x: Int, y: Int) = {
    val a = (x * 3.0 / Width) - 2.0;
    val b = (y * 2.0 / Height) - 1.0;
    
    recurrence(0.0, 0.0, a, b, 1)
}

// main
for (y <- Range(0, Height)) {
    val line = Range(0, Width).map(x => {
        val speed = checkDivergence(x, y)
        speed match {
            case MaxTrial => ' '
            case _ => ('A' + (speed - 1) % 26).asInstanceOf[Char]
        }
    })
    println(new String(line.toArray))
}