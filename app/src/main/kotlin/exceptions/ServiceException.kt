package exceptions

import data.enum.APIErrorCode

class ServiceException(val code: APIErrorCode, val technicalMessage: String? = null) : RuntimeException(code.name)