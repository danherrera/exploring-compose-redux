package com.example.composetest.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

typealias Dispatch<A> = (action: A) -> Unit
typealias Reducer<S, A> = (state: S, action: A) -> S
typealias Middleware<S, A> = (state: S, action: A, next: (A) -> S) -> S
typealias Redux<S, A> = (initialState: S, reducer: Reducer<S, A>, middlewares: List<Middleware<S, A>>) -> Pair<S, Dispatch<A>>

data class Store<S, A>(val state: S, val dispatch: Dispatch<A>)

fun <S, A> preReducerMiddleware(block: (state: S, action: A) -> Unit): Middleware<S, A> {
    return { state: S, action: A, next: (A) -> S ->
        block(state, action)
        next(action)
    }
}

fun <S, A> postReducerMiddleware(block: (previousState: S, latestState: S, action: A) -> Unit): Middleware<S, A> {
    return { state: S, action: A, next: (A) -> S ->
        val newState = next(action)
        block(state, newState, action)
        newState
    }
}

@Composable
fun <S, A> redux(
    initialState: S,
    reducer: Reducer<S, A> = { state, _ -> state },
    middlewares: List<Middleware<S, A>> = listOf { _, action, next -> next(action) }
): Store<S, A> {
    val state = remember { mutableStateOf(initialState) }
    val setState: (S) -> Unit = {
        if (it != state.value) state.value = it
    }
    val reducedMiddleware = middlewares.reduce { acc, middleware ->
        { state, action, next ->
            middleware(state, action) { nextAction ->
                acc(state, nextAction) { accAction ->
                    next(accAction)
                }
            }
        }
    }
    return Store(
        state = state.value,
        dispatch = { action ->
            reducedMiddleware(state.value, action) { middlewareAction ->
                reducer(state.value, middlewareAction).also(setState)
            }
        }
    )
}

abstract class ReduxActivity<S, A> : AppCompatActivity() {
    fun PreReducerMiddleware(block: (state: S, action: A) -> Unit): Middleware<S, A> =
        preReducerMiddleware(block)

    fun PostReducerMiddleware(block: (previousState: S, latestState: S, action: A) -> Unit): Middleware<S, A> =
        postReducerMiddleware(block)

    fun Middleware(middleware: Middleware<S, A>): Middleware<S, A> = middleware
    fun Reducer(reducer: Reducer<S, A>) = reducer

    @Composable
    fun Redux(
        initialState: S,
        reducer: Reducer<S, A> = { state, _ -> state },
        middlewares: List<Middleware<S, A>> = listOf { _, action, next -> next(action) }
    ): Store<S, A> = redux(initialState, reducer, middlewares)
}

interface StateAction<S, A> {
    fun PreReducerMiddleware(block: (state: S, action: A) -> Unit): Middleware<S, A> =
        preReducerMiddleware(block)

    fun PostReducerMiddleware(block: (previousState: S, latestState: S, action: A) -> Unit): Middleware<S, A> =
        postReducerMiddleware(block)

    fun Middleware(middleware: Middleware<S, A>): Middleware<S, A> = middleware
    fun Reducer(reducer: Reducer<S, A>) = reducer

    @Composable
    fun Redux(
        initialState: S,
        reducer: Reducer<S, A> = { state, _ -> state },
        middlewares: List<Middleware<S, A>> = listOf { _, action, next -> next(action) }
    ): Store<S, A> = redux(initialState, reducer, middlewares)
}

// Utility function to return new list but with item at index replaced with newItem
fun <T> List<T>.replaceIndexWith(index: Int, newItem: T): List<T> {
    return subList(0, index) + newItem + subList(index + 1, size)
}
