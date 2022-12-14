package br.com.softblue.bluefood.util;

public enum FileType {
	
	PNG("image/png", "png"),
	JPG("image/jpeg", "jpg");
	
	/*tipo tabelado que representa cada tipo de arquivo*/
	String mimeType;
	String extension;
	
	FileType(String mimeType, String extension){
		this.mimeType = mimeType;
		this.extension = extension;
	}
	
	public String getExtension() {
		return extension;
	}
	
	public String getMimeType() {
		return mimeType;
	}
	
	/*M?todo que compara se mimetype passado como parametro ? o mesmo do enum especifico que est? trabalhando*/
	public boolean sameOf(String mimeType) {
		return this.mimeType.equalsIgnoreCase(mimeType);
	}
	
	public static FileType of (String mimeType) {
		for(FileType fileType : values()) {
			if (fileType.sameOf(mimeType)) {
				return fileType;
			}
		}
		return null;
	}

	
}
