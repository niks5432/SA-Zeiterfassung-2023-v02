import java.sql.Date
import java.sql.Time
import java.time.LocalDate
import java.time.LocalDateTime

class Zeiterfassung {
    private val userid:         Int      = 0
    private var datum=          LocalDate.now()
    private var startzeit=      LocalDateTime.now()
    private var endzeit=        LocalDateTime.now()
    private var pausenzeit=     LocalDateTime.now()
    private var abwesenheitid:  Int   = 0
    private var zustandid:      Int   = 0

}