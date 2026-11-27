package li.nexus.nexus.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({
            CNPJNaoEncontradoException.class,
            CPFNaoEncontradoException.class,
            NomeNaoEncontradoException.class,
            MaterialNaoEncontradoException.class,
            ItemNaoEncontradoException.class,
            PagamentoNaoEncontradoException.class,
            PedidoNaoEncontradoException.class,
            SolicitacaoNaoEncontradaException.class,
            NenhumDadoEncontradoException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNaoEncontrado(RuntimeException exception) {
        return criarErrorResponse(HttpStatus.NOT_FOUND, exception);
    }

    @ExceptionHandler({
            CNPJJaCadastradoException.class,
            CPFJaCadastradoException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleConflito(RuntimeException exception) {
        return criarErrorResponse(HttpStatus.CONFLICT, exception);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleValidationException(
            MethodArgumentNotValidException exception) {
        Map<String, String> campos = new LinkedHashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(
                error -> campos.putIfAbsent(error.getField(), error.getDefaultMessage()));

        return new ValidationErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Erro de validação de dados",
                campos,
                LocalDateTime.now());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolation(ConstraintViolationException exception) {
        return criarErrorResponse(HttpStatus.BAD_REQUEST,
                new IllegalArgumentException("Um ou mais parâmetros são inválidos."));
    }

    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class,
            MissingServletRequestParameterException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleRequisicaoInvalida(Exception exception) {
        return criarErrorResponse(HttpStatus.BAD_REQUEST,
                new IllegalArgumentException("A requisição contém dados inválidos ou malformados."));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleViolacaoDeIntegridade(DataIntegrityViolationException exception) {
        return criarErrorResponse(HttpStatus.CONFLICT,
                new IllegalStateException("A operação conflita com os dados existentes."));
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleFalhaDeAutenticacao(AuthenticationException exception) {
        return criarErrorResponse(HttpStatus.UNAUTHORIZED,
                new IllegalArgumentException("E-mail ou senha inválidos."));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleErroInesperado(
            Exception exception, HttpServletRequest request) {
        log.error("Erro inesperado ao processar {}", request.getRequestURI(), exception);
        return criarErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                new IllegalStateException("Ocorreu um erro interno."));
    }

    private ErrorResponse criarErrorResponse(HttpStatus status, Exception exception) {
        return new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                exception.getMessage(),
                LocalDateTime.now());
    }
}
