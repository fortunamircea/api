package exceptions

import data.enum.APIErrorCode

class BadRequestException(val code: APIErrorCode, val technicalMessage: String? = null) : RuntimeException(code.name)