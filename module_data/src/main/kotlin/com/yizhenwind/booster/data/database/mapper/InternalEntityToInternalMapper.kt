package com.yizhenwind.booster.data.database.mapper

import com.yizhenwind.booster.common.model.Internal
import com.yizhenwind.booster.data.database.entity.InternalEntity
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/4/22
 */
class InternalEntityToInternalMapper @Inject constructor(): IMapper<InternalEntity, Internal> {

    override fun invoke(input: InternalEntity) =
        input.run { Internal(id, name) }

}