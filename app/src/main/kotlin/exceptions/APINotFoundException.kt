package exceptions

class APINotFoundException(val code: String) : RuntimeException(code)