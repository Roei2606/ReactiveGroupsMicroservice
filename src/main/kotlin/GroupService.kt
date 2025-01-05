package org.example

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface GroupService {
    fun create(group: GroupBoundary): Mono<GroupBoundary>
    fun getById(groupId: String): Mono<GroupBoundary>
    fun getGroups(page: Int, size: Int): Flux<GroupBoundary>
    fun updateGroup(groupId: String, updatedGroup: GroupBoundary): Mono<Void>
    fun deleteAllGroups(): Mono<Void>
    fun addUserToGroup(groupId: String, groupUser: GroupUserBoundary): Mono<Void>
    fun getUsersInGroup(groupId: String, page: Int, size: Int): Flux<GroupUserBoundary>
    fun getGroupsForUser(email: String, page: Int, size: Int): Flux<GroupBoundary>
    fun removeAllUsersFromGroup(groupId: String): Mono<Void>
}