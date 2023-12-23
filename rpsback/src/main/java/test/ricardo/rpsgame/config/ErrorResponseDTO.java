package test.ricardo.rpsgame.config;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponseDTO {

	private int status;
	private String code;
	private String message;
	private List<ErrorDTO> errors;

	public ErrorResponseDTO(int status, String code, String message) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
	}

}
