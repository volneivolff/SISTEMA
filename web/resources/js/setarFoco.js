function setaFoco(elemento) {
    var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
    if (keyCode == 13) {
        var i;
        for (i = 0; i < elemento.form.elements.length; i++)
            if (elemento == elemento.form.elements[i])
                break;
        i = (i + 1) % elemento.form.elements.length;
        elemento.form.elements[i].focus();
        event.preventDefault();
        return false;
    }
    return false;
}
