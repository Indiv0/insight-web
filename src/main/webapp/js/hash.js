function hashPassword() {
    var hash = CryptoJS.MD5(document.forms["login"]["j_password"].value).toString();
    document.forms["login"]["j_password"].value = hash;
}