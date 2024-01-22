fun menue(admin: String) {
    var schleifeEndeLogOut    = true

    do {
        if (admin == "Admin") {
            println("-----Menue----")
            println("Zeiterfassung Starten")
            println("Zeiterfassung Archiv")
            println("Zeiterfassungen Visieren")
            println("Benutzer erstellen")
            println("Log Out")
            var input = readln().toInt()
            when (input) {

                1 -> zeitEruieren()
                2 -> zeitArchiv()
                3 -> zeiterfassungVisieren()
                4 -> benutzerErstellen()
                5 -> schleifeEndeLogOut = false
            }
        } else {
            println("-----Menue----")
            println("Zeiterfassung Starten")
            println("Zeiterfassung Archiv")
            println("Log Out")

            var input = readln().toInt()
            when (input) {

                1 -> zeitEruieren()
                2 -> zeitArchiv()
                3 -> zeiterfassungVisieren()
                4 -> benutzerErstellen()
                5 -> schleifeEndeLogOut = false
            }
        }


    } while (schleifeEndeLogOut != true)
}