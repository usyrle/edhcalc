package elder.dragon.hipster.edhcalc.controller

import com.github.kittinunf.fuel.httpGet
import elder.dragon.hipster.edhcalc.model.EdhrecDeck
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
public class DeckController {

    @GetMapping
    fun render(model: Model) : String {
        model["deck"] = ""
        return "page"
    }

    @PostMapping
    fun process(model: Model, @RequestParam slug: String): String {
        val asyncHttp = "https://edhrec-json.s3.amazonaws.com/en/decks/$slug.json"
                .httpGet()
                .responseObject(EdhrecDeck.Deserializer()) { _, _, result ->
                    val (deck, error) = result

                    if (deck != null) {
                        model["deck"] = deck.decklist
                    } else {
                        model["deck"] = "Not found"
                    }
                }

        asyncHttp.join()

        return "page"
    }
}
