package br.com.softblue.bluefood.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class IOUtils {
	
	/*método de cópia copyInputStream consegue ler os bites da imagem, fileName é o nome do arquivo e outpuDir é o diretório*/
	public static void copy(InputStream in, String fileName, String outputDir) throws IOException{
		Files.copy(in, Paths.get(outputDir, fileName), StandardCopyOption.REPLACE_EXISTING);
	}
	
	/*Método que pega o caminho de arquivo e retorna os bytes*/
	
	public static byte[] getBytes(Path path) throws IOException{
		return Files.readAllBytes(path);
	}

}
