package com.yizhenwind.booster.data.database.sql

import androidx.annotation.IntDef
import com.yizhenwind.booster.common.ext.ifNullOrElse
import kotlin.reflect.KProperty

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/7
 */
internal const val MODE_INSERT = 1
internal const val MODE_DELETE = 2
internal const val MODE_UPDATE = 3
internal const val MODE_SELECT = 4

@IntDef(
    MODE_INSERT,
    MODE_DELETE,
    MODE_UPDATE,
    MODE_SELECT
)
private annotation class SQLMode

class SQLBuilder {

    @SQLMode
    private var mode: Int = 0

    private lateinit var table: String
    private val columnList: MutableList<String> = mutableListOf()
    private val valuesList: MutableList<List<Any?>> = mutableListOf()

    fun insert(table: String) {
        this.mode = MODE_INSERT
        this.table = table
    }

    fun delete(table: String) {
        this.mode = MODE_DELETE
        this.table = table
    }

    fun update(table: String) {
        this.mode = MODE_UPDATE
        this.table = table
    }

    fun select(table: String) {
        this.mode = MODE_SELECT
        this.table = table
    }

    fun columns(vararg columns: String) {
        columnList.apply {
            clear()
            addAll(columns)
        }
    }

    fun columns(vararg columns: KProperty<*>) {
        columnList.apply {
            clear()
            addAll(columns.map { it.name })
        }
    }

    fun values(vararg values: Any?) {
        valuesList.add(values.toList())
    }

    fun build(): String {
        val sqlBuilder = when (mode) {
            MODE_INSERT -> insertSQLBuilder()
            MODE_DELETE -> TODO("not implemented")
            MODE_UPDATE -> TODO("not implemented")
            MODE_SELECT -> TODO("not implemented")
            else -> throw IllegalStateException("must call insert/delete/update/select to set table")
        }

        return sqlBuilder.toString()
    }

    private fun insertSQLBuilder() =
        StringBuilder("INSERT INTO `$table`").apply {
            if (valuesList.isEmpty()) {
                throw IllegalArgumentException("insert values can not be empty")
            }
            if (columnList.isNotEmpty()) {
                append(" (")
                columnList.forEachIndexed { index, column ->
                    append("`")
                    append(column)
                    append("`")
                    if (index != columnList.size - 1) {
                        append(", ")
                    }
                }
                append(")")
            }
            append(" VALUES ")
            valuesList.forEachIndexed { index, values ->
                if (columnList.isNotEmpty() && values.size != columnList.size) {
                    throw IllegalArgumentException(
                        "value: ${values.joinToString(", ")} length not match " +
                                "column: ${columnList.joinToString(", ")} length."
                    )
                }
                append("(")
                values.forEachIndexed { valueIndex, value ->
                    value.ifNullOrElse({ append(value) }, {
                        when (value) {
                            is String -> {
                                append("`")
                                append(value)
                                append("`")
                            }
                            is Short, is Int, is Long, is Float, is Double, is ByteArray -> append(
                                value
                            )
                            else -> throw IllegalArgumentException("unsupported type of: $value")
                        }
                    })
                    if (valueIndex != values.size - 1) {
                        append(", ")
                    }
                }
                append(")")
                if (index != valuesList.size - 1) {
                    append(", ")
                }
            }
            append(";")
        }
}


fun sql(initializer: SQLBuilder.() -> Unit): SQLBuilder {
    return SQLBuilder().apply(initializer)
}

