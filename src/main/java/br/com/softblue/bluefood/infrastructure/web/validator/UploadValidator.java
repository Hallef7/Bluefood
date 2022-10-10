package br.com.softblue.bluefood.infrastructure.web.validator;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

import br.com.softblue.bluefood.util.FileType;

/*Classe para validação de imagens*/
public class UploadValidator implements ConstraintValidator<UploadConstraint, MultipartFile> {
	
	private List<FileType> acceptedFileTypes;
	
	/*Método para inicialização*/
	@Override
	public void initialize(UploadConstraint constraintAnnotation) {
		acceptedFileTypes = Arrays.asList(constraintAnnotation.acceptedTypes());
	}
	
	@Override
	public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
		if(multipartFile == null) {
			return true;
		}
		
		for(FileType fileType : acceptedFileTypes) {
			if(fileType.sameOf(multipartFile.getContentType())) {
				return true;
			}
		}
		return false;
	}
}
