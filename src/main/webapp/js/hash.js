function hashPassword(formName, fieldName) {
    var hash = CryptoJS.MD5(document.forms[formName][fieldName].value).toString();
    document.forms[formName][fieldName].value = hash;
}