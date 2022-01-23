package exceptions

import data.enum.APIErrorCode

class UnsupportedRequestException(val code: APIErrorCode, val technicalMessage: String? = null) :
    RuntimeException(code.name)