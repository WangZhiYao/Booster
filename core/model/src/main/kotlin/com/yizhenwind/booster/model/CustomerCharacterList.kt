package com.yizhenwind.booster.model

/**
 * 客户与角色
 *
 * @author WangZhiYao
 * @since 2022/6/23
 */
data class CustomerCharacterList(
    val customer: Customer,
    val characterList: List<Character>
) {

    data class Character(
        val id: Long,
        val name: String,
        val zone: Zone,
        val server: Server,
        val sect: Sect,
        val internal: Internal
    )
}