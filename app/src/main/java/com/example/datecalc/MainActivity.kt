import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var inputDate: EditText
    private lateinit var resultDate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputDate = findViewById(R.id.inputDate)
        resultDate = findViewById(R.id.resultDate)

        inputDate.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val input = s.toString().replace(".", "")
                if (input.length >= 2) {
                    val month = input.take(2).toIntOrNull() ?: 0
                    val year = input.drop(2).take(2).toIntOrNull() ?: 0
                    
                    if (month in 1..12 && year >= 0) {
                        // Вычисляем новую дату
                        var newMonth = month - 11
                        var newYear = year - 4
                        
                        if (newMonth <= 0) {
                            newMonth += 12
                            newYear -= 1
                        }
                        
                        // Форматируем результат
                        val formattedMonth = String.format("%02d", newMonth)
                        val formattedYear = String.format("%02d", newYear % 100)
                        
                        resultDate.text = "$formattedMonth.$formattedYear"
                    } else {
                        resultDate.text = "Некорректная дата"
                    }
                } else {
                    resultDate.text = ""
                }
                
                // Автоматически добавляем точку после 2 символов
                if (s?.length == 2 && !s.contains(".")) {
                    inputDate.setText("$s.")
                    inputDate.setSelection(3)
                }
            }
        })
    }
}
