package exceptions

import data.enum.APIErrorCode

class APIUnauthorizedException(val code: APIErrorCode) : RuntimeException(code.name)