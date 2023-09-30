package com.ethos.portifolioapi.exceptionhandler;

import com.ethos.portifolioapi.exception.PortifolioJaExisteException;
import com.ethos.portifolioapi.exception.PortifolioNaoExisteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class PortifolioExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        final ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        if (exception.getMessage().contains("Id inválido")) {
            problemDetail.setDetail("O Id informado é inválido");
        }
        if (exception.getMessage().contains("branco")) {
            switch (exception.getFieldError().getField()) {
                case "urlImagemPerfil" -> problemDetail.setDetail("O campo urlImagemPerfil não pode ser branco");
                case "urlImagemBackground" -> problemDetail.setDetail("O campo urlImagemBackground não pode ser branco");
                case "descricaoEmpresa" -> problemDetail.setDetail("O campo descricaoEmpresa não pode ser branco");
                case "sobreEmpresa" -> problemDetail.setDetail("O campo sobreEmpresa não pode ser branco");
                case "linkWebsiteEmpresa" -> problemDetail.setDetail("O campo linkWebsiteEmpresa não pode ser branco");
                case "dataEmpresaCertificada" -> problemDetail.setDetail("O campo dataEmpresaCertificada não pode ser branco");
            }
        } else if (exception.getMessage().contains("nulo") || exception.getMessage().contains("nula")) {
            switch (exception.getMessage()) {
                case "urlImagemPerfil" -> problemDetail.setDetail("O campo urlImagemPerfil social não pode ser nulo");
                case "urlImagemBackground" -> problemDetail.setDetail("O campo urlImagemBackground não pode ser nulo");
                case "descricaoEmpresa" -> problemDetail.setDetail("O campo descricaoEmpresa não pode ser nulo");
                case "sobreEmpresa" -> problemDetail.setDetail("O campo sobreEmpresa não pode ser nulo");
                case "linkWebsiteEmpresa" -> problemDetail.setDetail("O campo linkWebsiteEmpresa não pode ser nulo");
                case "dataEmpresaCertificada" -> problemDetail.setDetail("O campo dataEmpresaCertificada não pode ser nulo");
            }
        }
        problemDetail.setTitle("Corpo da requisição inválida");
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }


    @ExceptionHandler(PortifolioNaoExisteException.class)
    public ProblemDetail portifolioNaoExisteException(PortifolioNaoExisteException exception) {
        final ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setDetail(exception.getMessage());
        problemDetail.setTitle("Portifolio não encontrado");
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }
    @ExceptionHandler(PortifolioJaExisteException.class)
    public ProblemDetail portifolioJaExisteException(PortifolioJaExisteException exception) {
        final ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setDetail(exception.getMessage());
        problemDetail.setTitle("Portifolio já cadastrado");
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }
}
