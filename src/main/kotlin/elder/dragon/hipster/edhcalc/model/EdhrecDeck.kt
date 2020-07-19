package elder.dragon.hipster.edhcalc.model

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.util.regex.Pattern

val basics = listOf("Plains", "Island", "Swamp", "Mountain", "Forest")

data class EdhrecDeck(
        val basic: Int,
        val creature: Int,
        val enchantment: Int,
        val artifact: Int,
        val instant: Int,
        val sorcery: Int,
        val planeswalker: Int,
        val land: Int,
        val nonbasic: Int,
        val decklist: List<String>
) {
    class Deserializer: ResponseDeserializable<EdhrecDeck> {
        override fun deserialize(content: String) : EdhrecDeck{
            val deckJson = Gson().fromJson(content, JsonObject::class.java)

            val decklist = deckJson.get("description")
                    .asString.split("(\n|\r\n)+\\d?\\s?".toRegex())
                    .filter { card -> !card.contains("<a") && !basics.contains(card)}

            return EdhrecDeck(
                deckJson.get("basic").asInt,
                deckJson.get("creature").asInt,
                deckJson.get("enchantment").asInt,
                deckJson.get("artifact").asInt,
                deckJson.get("instant").asInt,
                deckJson.get("sorcery").asInt,
                deckJson.get("planeswalker").asInt,
                deckJson.get("land").asInt,
                deckJson.get("nonbasic").asInt,
                decklist
            )
        }
    }
}
