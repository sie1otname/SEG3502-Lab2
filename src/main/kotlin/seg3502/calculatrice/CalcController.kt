package seg3502.calculatrice

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class CalcController {
    @GetMapping("/")
    fun home(model: Model): String {
        model.addAttribute("first", "")
        model.addAttribute("second", "")
        model.addAttribute("operation", "+")
        return "home"
    }

    @GetMapping("/calculate")
    fun calculate(
        @RequestParam first: String,
        @RequestParam second: String,
        @RequestParam operation: String,
        model: Model
    ): String {
        model.addAttribute("first", first)
        model.addAttribute("second", second)
        model.addAttribute("operation", operation)

        val a = first.toDoubleOrNull()
        val b = second.toDoubleOrNull()
        if (a == null || b == null || !a.isFinite() || !b.isFinite()) {
            model.addAttribute("error", "Veuillez entrer deux nombres valides.")
            return "home"
        }

        val result = when (operation) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> if (b != 0.0) a / b else null
            else -> null
        }
        if (result == null || !result.isFinite()) {
            model.addAttribute("error", "Calcul impossible : vérifiez l'opération ou la division par zéro.")
        } else {
            model.addAttribute("result", result)
        }
        return "home"
    }
}
