package dev.einsjannis.acacia.protocol

class Recipe : PacketObject() {
    var type by id()
    var identifier by string()
    var width by varInt().onlyIf({ type == Identifier("minecraft", "crafting_shaped") }, {  })
    var height by varInt().onlyIf({ type == Identifier("minecraft", "crafting_shaped") }, {  })
    var base by `object`(::RecipeIngredient).onlyIf({ type == Identifier("minecraft", "smithing") }, {  })
    var addition by `object`(::RecipeIngredient).onlyIf({ type == Identifier("minecraft", "smithing") }, {  })
    var group by string().onlyIf({
        type == Identifier("minecraft", "crafting_shapeless") ||
        type == Identifier("minecraft", "crafting_shaped") ||
        type == Identifier("minecraft", "smelting") ||
        type == Identifier("minecraft", "blasting") ||
        type == Identifier("minecraft", "smoking") ||
        type == Identifier("minecraft", "campfire_cooking") ||
        type == Identifier("minecraft", "campfire_cooking") ||
        type == Identifier("minecraft", "stonecutting")
    }, {  })
    var ingredient by `object`(::RecipeIngredient).onlyIf({
        type == Identifier("minecraft", "smelting") ||
        type == Identifier("minecraft", "blasting") ||
        type == Identifier("minecraft", "smoking") ||
        type == Identifier("minecraft", "campfire_cooking")
    }, {  })
    var ingredientsCount by varInt().onlyIf({ type == Identifier("minecraft", "crafting_shapeless") }, {  })
    var ingredients by slot().array({ if (type == Identifier("minecraft", "crafting_shapeless")) ingredientsCount!! else (height!! * width!!) }, { if (type == Identifier("minecraft", "crafting_shapeless")) ingredientsCount = it }).onlyIf({
        type == Identifier("minecraft", "crafting_shapeless") ||
        type == Identifier("minecraft", "crafting_shaped")
    }, {  })
    var result by slot().onlyIf({
        type == Identifier("minecraft", "crafting_shapeless") ||
        type == Identifier("minecraft", "crafting_shaped") ||
        type == Identifier("minecraft", "smelting") ||
        type == Identifier("minecraft", "blasting") ||
        type == Identifier("minecraft", "smoking") ||
        type == Identifier("minecraft", "campfire_cooking") ||
        type == Identifier("minecraft", "stonecutting") ||
        type == Identifier("minecraft", "smithing") ||
        type == Identifier("minecraft", "smithing")
    }, {  })
    var experience by float().onlyIf({
        type == Identifier("minecraft", "smelting") ||
        type == Identifier("minecraft", "blasting") ||
        type == Identifier("minecraft", "smoking") ||
        type == Identifier("minecraft", "campfire_cooking")
    }, {  })
    var cookingTime by varInt().onlyIf({
        type == Identifier("minecraft", "smelting") ||
        type == Identifier("minecraft", "blasting") ||
        type == Identifier("minecraft", "smoking") ||
        type == Identifier("minecraft", "campfire_cooking")
    }, {  })
}
