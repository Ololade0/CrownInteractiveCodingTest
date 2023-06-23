package crownInteractive.codingtest.codingtest.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerCannotBeFound extends RuntimeException{

    public CustomerCannotBeFound(String message) {
        super(message);
    }


}

