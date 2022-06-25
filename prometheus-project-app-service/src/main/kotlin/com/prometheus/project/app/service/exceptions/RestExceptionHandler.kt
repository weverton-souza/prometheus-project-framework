package com.prometheus.project.app.service.exceptions

import com.prometheus.project.app.service.reponse.ResponseError
import java.util.Date
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(ex: ResourceNotFoundException): ResponseError {
        val notFoundDetails = ResourceNotFoundDetails(Date().time, ex.javaClass.name)

        return ResponseError(
            notFoundDetails,
            HttpStatus.NOT_FOUND.value(),
            HttpStatus.NOT_FOUND.reasonPhrase
        )
    }

//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MissingServletRequestParameterException::class)
//    protected fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, request: WebRequest): ResponseEntity<Any> {
//
//        val fieldExceptionDetails = HashMap<String, MutableList<String?>>()
//        val detailsList: MutableList<FieldExceptionDetails> = ArrayList()
//        val fieldErrors = ex.bindingResult.fieldErrors
//
//        for (e in fieldErrors) {
//            fieldExceptionDetails.computeIfAbsent(e.field) { ArrayList() }
//            fieldExceptionDetails[e.field]!!.add(e.defaultMessage)
//        }
//
//        for (entry in fieldExceptionDetails.entries) {
//            detailsList.add(FieldExceptionDetails(entry.key, entry.value))
//        }
//
//        val validationExceptionDetails: ValidationExceptionDetails = ValidationExceptionDetails(
//            detailsList, Date().time, ex.javaClass.name
//        )
//
//        val response: Response<ValidationExceptionDetails> = Response(
//            validationExceptionDetails,
//            HttpStatus.BAD_REQUEST.value(),
//            HttpStatus.BAD_REQUEST.reasonPhrase
//        )
//
//        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
//    }


}