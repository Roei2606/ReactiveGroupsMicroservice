package org.example

import org.springframework.stereotype.Component

@Component
class GroupConverter {
    fun toEntity(boundary: GroupBoundary): GroupEntity {
        return GroupEntity(
            id = boundary.id,
            name = boundary.name,
            creationDate = boundary.creationDate,
            description = boundary.description
        )
    }

    fun toBoundary(entity: GroupEntity): GroupBoundary {
        return GroupBoundary(
            id = entity.id,
            name = entity.name,
            creationDate = entity.creationDate,
            description = entity.description
        )
    }
}