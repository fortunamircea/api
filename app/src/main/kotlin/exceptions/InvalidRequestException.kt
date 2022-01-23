package exceptions

import web.ValidationError

class InvalidRequestException(val code: String, val details: List<ValidationError>) : RuntimeException(code)