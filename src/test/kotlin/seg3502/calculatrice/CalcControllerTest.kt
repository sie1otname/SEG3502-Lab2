package seg3502.calculatrice

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
class CalcControllerTest(@Autowired val mvc: MockMvc) {
    @Test
    fun addition() {
        mvc.get("/calculate") {
            param("first", "2")
            param("second", "3")
            param("operation", "+")
        }.andExpect { status { isOk() }; model { attributeExists("result") } }
    }
    @Test
    fun divisionParZero() {
        mvc.get("/calculate") {
            param("first", "2")
            param("second", "0")
            param("operation", "/")
        }.andExpect { status { isOk() }; model { attributeExists("error") } }
    }
}
