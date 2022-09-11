package com.yizhenwind.booster.data

import com.yizhenwind.booster.common.constant.ContactType
import com.yizhenwind.booster.common.model.Customer
import com.yizhenwind.booster.data.database.sql.sql
import org.junit.Test

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/8
 */
class SQLBuilderUnitTest {

    @Test
    fun insert() {
        val sql = sql {
            insert("customer")
            columns(
                Customer::name,
                Customer::contactType,
                Customer::contact,
                Customer::remark,
                Customer::createTime
            )
            values(
                "customer 1",
                ContactType.QQ.name,
                "393893729",
                null,
                System.currentTimeMillis()
            )
            values(
                "customer 2",
                ContactType.WECHAT.name,
                "levan-wzy",
                null,
                System.currentTimeMillis()
            )
            values(
                "customer 3",
                ContactType.PHONE.name,
                "15088686524",
                null,
                System.currentTimeMillis()
            )
        }.build()

        println(sql)
    }
}