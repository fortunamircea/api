package web

sealed class Result(val status: ResultStatus) {
    object Ok : Result(ResultStatus.SUCCESS)
    class Object<T>(val data: T) : Result(ResultStatus.SUCCESS)
    class List<T>(val data: kotlin.collections.List<T>) : Result(ResultStatus.SUCCESS)
    data class Page<T>(val data: kotlin.collections.List<T>,
                  val paging: PagingDto
    ) : Result(ResultStatus.SUCCESS)

    class Error(val error: ErrorDto) : Result(ResultStatus.FAILURE)
}

enum class ResultStatus {
    SUCCESS, FAILURE
}

class PagingDto(val offset: Int,
                val limit: Int,
                val totalCount: Long)

sealed class ErrorDto(val code: String) {
    class General(code: String) : ErrorDto(code)
    class Validation(code: String, val errors: List<ValidationError>) : ErrorDto(code)
}

class ValidationError(val field: String, val code: String, val arguments: Map<String, Any>)