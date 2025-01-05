package org.example

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.text.SimpleDateFormat
import java.util.*

@Service
class GroupServiceImpl(
    val groupCrud: GroupCrud,
    val groupConverter: GroupConverter
) : GroupService {
    override fun create(group: GroupBoundary): Mono<GroupBoundary> {
        return Mono.just(group)
            .map {
                if (it.name.isNullOrEmpty() || it.description.isNullOrEmpty()) {
                    throw IllegalArgumentException("Name and Description cannot be empty")
                }
                it.id = null
                it.creationDate = SimpleDateFormat("dd-MM-yyyy").format(Date())
                this.groupConverter.toEntity(it)
            }
            .log()
            .flatMap { this.groupCrud.save(it) }
            .map { this.groupConverter.toBoundary(it) }
            .log()
    }

    override fun getById(groupId: String): Mono<GroupBoundary> {
        return this.groupCrud
            .findById(groupId)
            .map { this.groupConverter.toBoundary(it) }
            .log()
            .switchIfEmpty(Mono.error(GroupNotFoundException("Group with ID $groupId not found")))
    }

        override fun getGroups(page: Int, size: Int): Flux<GroupBoundary> {
        return this.groupCrud
            .findAllByIdNotNull(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")))
            .log()
            .map { this.groupConverter.toBoundary(it) }
    }

    override fun updateGroup(groupId: String, updatedGroup: GroupBoundary): Mono<Void> {
        return this.groupCrud
            .findById(groupId)
            .log()
            .switchIfEmpty(Mono.error(GroupNotFoundException(groupId)))
            .flatMap { existingGroup ->
                if (!updatedGroup.name.isNullOrEmpty()) {
                    existingGroup.name = updatedGroup.name
                }
                if (!updatedGroup.description.isNullOrEmpty()) {
                    existingGroup.description = updatedGroup.description
                }
                this.groupCrud.save(existingGroup)
            }
            .then()
            .log()
    }

    override fun deleteAllGroups(): Mono<Void> {
        return this.groupCrud
            .deleteAll()
            .log()
    }

    override fun addUserToGroup(groupId: String, groupUser: GroupUserBoundary): Mono<Void> {
        if (groupUser.email.isNullOrEmpty()) {
            return Mono.error(IllegalArgumentException("Email cannot be null or empty"))
        }

        return this.groupCrud
            .findById(groupId)
            .switchIfEmpty(Mono.error(GroupNotFoundException(groupId)))
            .flatMap { group ->
                if (group.users == null) {
                    group.users = mutableSetOf()
                }
                if (group.users!!.contains(groupUser.email)) {
                    return@flatMap Mono.empty<Void>()
                }
                group.users!!.add(groupUser.email!!)
                this.groupCrud.save(group)
            }
            .then()
            .log()
    }

    override fun getUsersInGroup(groupId: String, page: Int, size: Int): Flux<GroupUserBoundary> {
        return this.groupCrud
            .findById(groupId)
            .switchIfEmpty(Mono.error(GroupNotFoundException(groupId)))
            .log()
            .flatMapMany { group ->
                val sortedUsers = group.users?.sortedWith(String.CASE_INSENSITIVE_ORDER) ?: listOf()
                Flux.fromIterable(sortedUsers)
                    .skip((page * size).toLong())
                    .take(size.toLong())
                    .map { GroupUserBoundary(email = it) }
                    .log()
            }
    }

    override fun getGroupsForUser(email: String, page: Int, size: Int): Flux<GroupBoundary> {
        return this.groupCrud
            .findAllByUsersContaining(email)
            .log()
            .sort(compareBy { it.id })
            .skip((page * size).toLong())
            .take(size.toLong())
            .map { this.groupConverter.toBoundary(it) }
            .log()

    }

    override fun removeAllUsersFromGroup(groupId: String): Mono<Void> {
        return this.groupCrud
            .findById(groupId)
            .switchIfEmpty(Mono.error(GroupNotFoundException(groupId)))
            .flatMap { group ->
                group.users?.clear()
                this.groupCrud.save(group)
            }
            .then() 
            .log()
    }
}