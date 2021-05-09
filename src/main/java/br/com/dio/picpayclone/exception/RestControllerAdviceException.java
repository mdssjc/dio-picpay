package br.com.dio.picpayclone.exception;

import br.com.dio.picpayclone.dto.ErrorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestControllerAdvice
public class RestControllerAdviceException {

    private final MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorDTO> handle(MethodArgumentNotValidException exception) {
        var dto = new ArrayList<ErrorDTO>();

        var fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            var mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            var erro = new ErrorDTO(e.getField(), mensagem);
            dto.add(erro);
        });

        return dto;
    }
}
