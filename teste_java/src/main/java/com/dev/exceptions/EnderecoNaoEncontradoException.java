package com.dev.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class EnderecoNaoEncontradoException extends RuntimeException {

  public EnderecoNaoEncontradoException(String message) {
    super(message);
  }
}

