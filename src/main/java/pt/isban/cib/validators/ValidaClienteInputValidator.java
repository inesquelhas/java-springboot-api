package pt.isban.cib.validators;

import pt.isban.cib.annotations.ValidaClienteInput;
import pt.isban.cib.dto.ClienteInsertDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ValidaClienteInputValidator implements ConstraintValidator<ValidaClienteInput, ClienteInsertDTO> {

    //@Override
    //public void initialize(ClienteInsertDTO ann){}

    @Override
    public boolean isValid(ClienteInsertDTO dto, ConstraintValidatorContext context){

        Map<String, String> errorsMap = new HashMap<>();

        //        - Nivel de parametro
        //        - Para validar a senha (a+A+9+)
        //                - Datas validas
        //                - Nivel de objetos
        //        - Email com combinação de nome & sobrenome @ qualquer coisa
        //        - Implementar no ClienteNewDTO e pôr NotNull em (nome/email) p n dar conflito com o PUT

        final String message = "Não pode ter mais do que 100 caracteres";

        if (dto.getEmail() != null && dto.getEmail().length() > 100) {
            errorsMap.put("email", message);
        }

        if (dto.getNome() != null && dto.getNome().length() > 100) {
            errorsMap.put("nome", message);
        }

        if (dto.getPassword() != null && dto.getPassword().length() > 100) {
            errorsMap.put("password", message);
        }

        Date sysdate = new Date();

        if(dto.getDtNasc().after(sysdate)) {
            errorsMap.put("dtNasc", "Data de Nascimento não pode ser superior à data actual");
        }

        for (Map.Entry<String, String> entry : errorsMap.entrySet()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    entry.getValue())
                    .addPropertyNode(entry.getKey())
                    .addConstraintViolation();
        }
        return errorsMap.isEmpty();
    }
}
