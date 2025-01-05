package org.example

import java.util.*

class GroupBoundary(
    var id: String?,
    var name: String?,
    var creationDate: String?,
    var description: String?
) {
    constructor() : this(null, null, null, null)

    override fun toString(): String {
        return "{id=$id, name=$name, creationDate=$creationDate, description=$description}"
    }
}