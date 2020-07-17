package elder.dragon.hipster.edhcalc.model

import com.github.kittinunf.fuel.core.ResponseDeserializable

data class EdhrecDeck(
        val basic: Int,
        val creature: Int,
        val enchantment: Int,
        val sorcery: Int,
        val planeswalker: Int,
        val land: Int,
        val nonbasic: Int,
        val description: String
)
