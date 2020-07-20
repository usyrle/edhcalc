package elder.dragon.hipster.edhcalc.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class EdhrecDeckTest {

    @Test
    fun `deserialize should generate all properties, deck list without basics`() {
        val testString = """
        {
          "basic": 18,
          "nonbasic": 18,
          "creature": 22,
          "instant": 6,
          "sorcery": 9,
          "enchantment": 4,
          "artifact": 21,
          "planeswalker": 1,
          "land": 36,
          "description": "<a href=\"https://www.cardkingdom.com/builder?utm_campaign=edhrec\">Buy this decklist from Card Kingdom</a>\n<a href=\"https://store.tcgplayer.com/massentry?partner=EDHREC\">Buy this decklist from TCGplayer</a>\n1 Nazahn, Revered Bladesmith\n\n1 Balan, Wandering Knight\n1 Dispatch\n\n1 Cultivate\n1 Hammer of Nazahn\n1 Sigarda's Aid\n1 Nahiri, the Lithomancer\n1 Inventors' Fair\n16 Plains"
        }
        """.trimIndent()

        val expectedDeck = listOf("Nazahn, Revered Bladesmith", "Balan, Wandering Knight", "Dispatch", "Cultivate", "Hammer of Nazahn", "Sigarda's Aid", "Nahiri, the Lithomancer", "Inventors' Fair")

        val actual = EdhrecDeck.Deserializer().deserialize(testString)

        assertThat(actual.basic).isEqualTo(18)
        assertThat(actual.nonbasic).isEqualTo(18)
        assertThat(actual.creature).isEqualTo(22)
        assertThat(actual.instant).isEqualTo(6)
        assertThat(actual.sorcery).isEqualTo(9)
        assertThat(actual.enchantment).isEqualTo(4)
        assertThat(actual.artifact).isEqualTo(21)
        assertThat(actual.planeswalker).isEqualTo(1)
        assertThat(actual.land).isEqualTo(36)
        assertThat(actual.decklist).isEqualTo(expectedDeck)
    }
}
