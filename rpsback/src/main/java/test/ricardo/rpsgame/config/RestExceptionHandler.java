package test.ricardo.rpsgame.config;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import test.ricardo.rpsgame.core.game.domain.IncorrectGameStatusException;
import test.ricardo.rpsgame.core.shared.application.NotFoundException;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler({ IncorrectGameStatusException.class })
	@ResponseBody
	public ResponseEntity<ErrorResponseDTO> handleIncorrectGameStatusException(IncorrectGameStatusException exception) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ErrorResponseDTO(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.toString(),
						exception.getMessage()));
	}

	@ExceptionHandler({ NotFoundException.class })
	@ResponseBody
	public ResponseEntity<ErrorResponseDTO> handleNotFoundException(NotFoundException exception) {
		return ResponseEntity.notFound()
				.build();
	}

	@ExceptionHandler({ Exception.class })
	@ResponseBody
	public ResponseEntity<ErrorResponseDTO> unhandledErrors(final HttpServletRequest req, final Exception e)
			throws Exception {
		log.error("Handling exception", e);
		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
			throw e;
		}

		return ResponseEntity.internalServerError()
				.body(new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
						HttpStatus.INTERNAL_SERVER_ERROR.toString(), NestedExceptionUtils.getMostSpecificCause(e)
								.getMessage()));
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	@ResponseBody
	public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(final HttpServletRequest req,
			final HttpServletResponse response, final Exception ex) {
		response.setStatus(HttpStatus.BAD_REQUEST.value());

		List<ErrorDTO> errorList = new ArrayList<>();
		if (ex instanceof MethodArgumentNotValidException) {
			final BindingResult result = ((MethodArgumentNotValidException) ex).getBindingResult();
			errorList = result.getFieldErrors()
					.stream()
					.map(error -> new ErrorDTO(error.getField(), error.getCode(), error.getDefaultMessage()))
					.collect(Collectors.toList());
		}

		return ResponseEntity.badRequest()
				.body(new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(),
						"Invalid arguments", errorList));
	}
}
