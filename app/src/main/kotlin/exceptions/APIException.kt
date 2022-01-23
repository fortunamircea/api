package exceptions

import data.enum.APIErrorCode

class APIException(val code: APIErrorCode, cause: Throwable? = null, val error: Any? = null) :
    RuntimeException(code.name, cause)