package com.epam.boot.exception.handler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.epam.boot.exception.PersonNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(SQLException.class)
  public String handleSqlError(SQLException e, Model model, HttpServletRequest req) {
    model.addAttribute("errorMessage", "Database error: " + e.getMessage());
    model.addAttribute("timestamp", LocalDateTime.now());
    model.addAttribute("path", req.getRequestURI());

    return "error/dbError";
  }

  @ResponseStatus(NOT_FOUND)
  @ExceptionHandler(PersonNotFoundException.class)
  public Object handlePersonNotFound(PersonNotFoundException ex, WebRequest request) {
    String contentType = request.getHeader("Accept");

    if (contentType != null && contentType.contains("application/json")) {
      Map<String, String> error = new HashMap<>();
      error.put("error", ex.getMessage());
      return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    ModelAndView mav = new ModelAndView("error/404");
    mav.addObject("errorMessage", ex.getMessage());
    return mav;
  }
}
