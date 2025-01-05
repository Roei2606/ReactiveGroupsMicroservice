package org.example

class GroupUserBoundary(
    var email: String?
) {
    constructor() : this(null)

    override fun toString(): String {
        return "{email=$email}"
    }
}