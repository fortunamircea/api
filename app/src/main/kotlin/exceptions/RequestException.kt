package exceptions

import exceptions.APIException

class RequestException(val code: APIException) : RuntimeException(code.message)