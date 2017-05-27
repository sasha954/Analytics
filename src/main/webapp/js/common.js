/**
 * Created by fomenko on 13.03.2017.
 */

function loginPopUp() {
    var logBtn = document.getElementById('login');



    logBtn.onclick = function (e) {
        e.preventDefault();
    }
}



window.onload = function () {
    loginPopUp();
}