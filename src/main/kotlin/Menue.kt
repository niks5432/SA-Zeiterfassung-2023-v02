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
                else -> {
                    println("Falsche eingabe")
                }
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
                5 -> schleifeEndeLogOut = false
                else -> {
                    println("Falsche eingabe")
                }
            }
        }
    } while (schleifeEndeLogOut == true)
    println("Sie wurden Ausgeloggt")
    main()
}