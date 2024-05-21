package com.jorgesm.compose_marvel_api.utils

import com.jorgesm.domain.model.Character

fun getOneCharacterDetails(): Character {
    return Character(
        id = 1016823,
        name = "Abomination (Ultimate)",
        description = "Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction!",
        thumbnail = "https://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16/portrait_medium.jpg",
        comics = 9,
        series = 16,
        stories = 100,
        events = 2600,
    )
}
