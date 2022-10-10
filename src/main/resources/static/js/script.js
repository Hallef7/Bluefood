function isNumberKey(evt){
	/*adaptação para funcionar em todos navegadores, if para verificar os campos numericos para aceitar somente 123456789*/
		var charCode = (evt.which) ? evt.which : evt.keyCode;
	
	if ((charCode >= 48 && charCode <= 57) || charCode <= 31) {
		return true;
	}
	
	return false;
}

function searchRest(categoriaId) {
	
	var t = document.getElementById("searchType");
	
	if (categoriaId == null) {
		t.value = "Texto";
	
	} else {
		t.value = "Categoria";
		document.getElementById("categoriaId").value = categoriaId;
	}
	
	document.getElementById("form").submit();
}

/*colocoa o valor cmd no campo e submete o formulário */
function setCmd(cmd) {
	document.getElementById("cmd").value = cmd;
	document.getElementById("form").submit();

}

function filterCardapio (categoria){
	document.getElementById("categoria").value = categoria;
	document.getElementById("filterForm").submit();
}
