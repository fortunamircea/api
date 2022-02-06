package web

import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.google.common.base.CaseFormat
import data.enum.APIErrorCode
import exceptions.*
import org.slf4j.LoggerFactory
import org.springframework.context.support.DefaultMessageSourceResolvable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.multipart.MultipartException
import java.util.*

@ControllerAdvice
class GlobalExceptionHandler {

    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    private fun convertToUnderscore(string: String): String {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, string)
    }

    @ExceptionHandler(Throwable::class)
    fun handleAllException(ex: Throwable): ResponseEntity<ResponseFail> {
        logger.error(ex.message, ex)

        return ResponseEntity(ResponseFail(ResponseErrorDto("INTERNAL_ERROR")), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(MultipartException::class)
    fun handleMultipartException(ex: MultipartException): ResponseEntity<ResponseFail> {
        logger.error(ex.message, ex)

        return ResponseEntity(ResponseFail(ResponseErrorDto("INVALID_MULTIPART_VALUE")), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(ex: BadRequestException): ResponseEntity<ResponseFail> {
        logger.error(ex.message, ex)

        return ResponseEntity(ResponseFail(ResponseErrorDto(ex.code.name)), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(UnsupportedRequestException::class)
    fun handleBadRequestException(ex: UnsupportedRequestException): ResponseEntity<ResponseFail> {
        logger.error(ex.message, ex)

        return ResponseEntity(ResponseFail(ResponseErrorDto(ex.code.name)), HttpStatus.OK)
    }

    @ExceptionHandler(APIUnauthorizedException::class)
    fun handleGPCUnauthorizedException(ex: APIUnauthorizedException): ResponseEntity<ResponseFail> {
        logger.error(ex.message, ex)

        return ResponseEntity(ResponseFail(ResponseErrorDto("UNAUTHORIZED")), HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(InvalidRequestException::class)
    fun handleInvalidRequestException(ex: InvalidRequestException): ResponseEntity<ResponseValidationFail> {
        logger.error(ex.message, ex)

        return ResponseEntity(ResponseValidationFail(ErrorValidationDto(ex.code, ex.details)), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(GeneralException::class)
    fun handleGeneralException(ex: GeneralException): ResponseEntity<ResponseFail> {
        logger.error(ex.message, ex)

        return ResponseEntity(ResponseFail(ResponseErrorDto(ex.code)), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(APIException::class)
    fun handleGPCException(ex: APIException): ResponseEntity<ResponseFail> {
        logger.warn(ex.message, ex)

        return ResponseEntity(
            ResponseFail(ResponseErrorDto(ex.code.name, ex.message, ex.error)),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(APINotFoundException::class)
    fun handleNotFoundException(ex: APINotFoundException): ResponseEntity<ResponseFail> {
        return ResponseEntity(ResponseFail(ResponseErrorDto(ex.code)), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<Result.Error> {
        logger.warn(ex.message, ex)

        val errors = ex.bindingResult.allErrors.map { e ->
            val args = e.arguments ?: throw RuntimeException("Missing arguments")
            val code = e.code?.let { convertToUnderscore(it) } ?: throw RuntimeException("Missing arguments")

            val field = (args[0] as DefaultMessageSourceResolvable).code as String

            val arguments = when (code) {
                "size" -> mapOf("min" to args[2], "max" to args[1])
                "min" -> mapOf("value" to args[1])
                "max" -> mapOf("value" to args[1])
                "email" -> mapOf()
                "pattern" -> mapOf()
                else -> throw RuntimeException("Unhandled validation code: $code")
            }

            ValidationError(field, code.uppercase(Locale.getDefault()), arguments)
        }

        val dto = ErrorDto.Validation(APIErrorCode.REQUEST_INVALID.name, errors)
        return ResponseEntity(Result.Error(dto), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handlingHttpMessageNotReadableException(ex: HttpMessageNotReadableException): ResponseEntity<ResponseFail> {
        logger.error(ex.message, ex)
        if (ex.cause is MissingKotlinParameterException) {
            return handlingMissingKotlinParameterException(ex.cause as MissingKotlinParameterException)
        }
        return ResponseEntity(
            ResponseFail(ResponseErrorDto("BAD_REQUEST", "Request schema is not valid")),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(MissingKotlinParameterException::class)
    fun handlingMissingKotlinParameterException(ex: MissingKotlinParameterException): ResponseEntity<ResponseFail> {
        logger.error(ex.message, ex)
        val fieldName = ex.path[0].fieldName
        return ResponseEntity(
            ResponseFail(
                ResponseErrorDto(
                    "BAD_REQUEST", "Missing parameter in your request, check request body. " +
                            "Detail : missing $fieldName field"
                )
            ), HttpStatus.BAD_REQUEST
        )
    }
}

class ResponseFail(val error: ResponseErrorDto)
class ResponseValidationFail(val error: ErrorValidationDto)

class ResponseErrorDto(val code: String, val message: String? = null, val errors: Any? = null)
class ErrorValidationDto(val code: String, val details: List<ValidationError>)