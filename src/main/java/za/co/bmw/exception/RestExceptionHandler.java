package za.co.bmw.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
    public class RestExceptionHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler(value = { CustomerNotFoundException.class })
        protected ResponseEntity<Object> handleCustomerNotFound(CustomerNotFoundException ex, WebRequest request) {
            String message = "Customer not found";
            return handleExceptionInternal(ex, message, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
        }

        @ExceptionHandler(value = { OrderNotFoundException.class })
        protected ResponseEntity<Object> handleOrderNotFound(OrderNotFoundException ex, WebRequest request) {
            String message = "Order not found";
            return handleExceptionInternal(ex, message, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
        }

        // Add more exception handling methods as needed
    }