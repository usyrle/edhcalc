package elder.dragon.hipster.edhcalc.controller

import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import elder.dragon.hipster.edhcalc.model.EdhrecDeck
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
public class DeckController {

    @GetMapping("/")
    fun render(model: Model): String {
        var asyncHttp = "https://edhrec-json.s3.amazonaws.com/en/decks/nazahn-revered-bladesmith.json"
                .httpGet()
                .responseObject<EdhrecDeck> { _, _, result ->
                    model["deck"] = result.get().description
                }

        asyncHttp.join()

        return "page"
    }
}
