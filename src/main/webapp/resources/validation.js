function Registration(){

    var _username =null;
    var _password = null;

    initializeFields = function () {
        _username =  $('#userName');
        _password = $('#password');
    };

    this.init = function () {
        initializeFields();
        _username.blur(function () {
            var name = _username.val();
            var userMessage = $('#userNameMessage');
            var id = $('#id').val();

            //checking for spaces.
            if(name.indexOf(" ")>=0){
                userMessage.css("color", "red");
                userMessage.html("Sorry, the username can't contain spaces.");
                return;
            }

            var resp = registration.checkUsername();
            //making sure with id that these messages are not shown at the time of editing. ie. id is not 0.
            if (resp === "Yes" && id==0) {
                userMessage.css("color", "red");
                userMessage.html("Sorry, the username is not available.");
            }
            else if (resp === "No" && id==0) {
                userMessage.css("color", "green");
                userMessage.html("Congrats, Your username is available.");
            }
        });
    };

    this.checkUsername = function () {
        initializeFields();
        var name = _username.val();
        var result = null;
        var scriptUrl = "/rest/queryusername?userName=" + name;
        $.ajax({
            url: scriptUrl,
            type: 'get',
            dataType: 'text',
            async: false,
            success: function (data) {
                result = data;
            }
        });
        return result;
    };

    this.checkStrength = function () {
        initializeFields();
        var password = _password.val();
        var strength = 0;
        var passwordMessage = $('#passwordMessage');
        if (password.length==0){
            passwordMessage.css("color", "red");
            return "can't be empty";
        }
        if (password.length < 6) {
            passwordMessage.css("color", "red");
            return 'Too short'
        }
        if (password.length > 7) strength += 1;
        if (password.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/)) strength += 1;
        if (password.match(/([a-zA-Z])/) && password.match(/([0-9])/)) strength += 1;
        if (password.match(/([!,%,&,@,#,$,^,*,?,_,~])/)) strength += 1;
        if (strength < 2) {
            passwordMessage.css("color", "red");
            return 'Weak'
        }
        else if (strength === 2) {
            passwordMessage.css("color", "steelblue");
            return 'Good'
        }
        else {
            passwordMessage.css("color", "green");
            return 'Strong'
        }
    };
}

var registration = new Registration();

$(document).ready(function () {
    registration.init();
    $('#password').keyup(function () {
        $('#passwordMessage').html(this.checkStrength())
    });
});

function clearErrors() {
    $('.errmsg').html("");
}

//this will be called on submit only, unlike the above.
function checkVal() {
    this.id= $('#id').val();

    this.userName = $('#userName').val();
    this.usernameMesssage = $('#userNameMessage');

    this.password = $('#password').val();
    this.passwordMessage = $('#passwordMessage');

    this.name = $('#name').val();
    this.nameMessage = $('#nameMessage');

    this.salary = $('#salary').val();
    this.salaryMessage = $('#salaryMessage');

    this.city = jQuery("#city").find("option:selected").text();
    this.cityMessage = $('#cityMessage');
    //if username is empty
    if (!this.userName) {
        this.usernameMesssage.css("color", "red");
        this.usernameMesssage.html("Sorry, the username can't be empty.");
        return false;
    }
    // if username contains spaces
    else if(this.userName.indexOf(" ")>=0){
        this.usernameMesssage.css("color", "red");
        this.usernameMesssage.html("Sorry, the username can't contain spaces");
        return false;
    }

    // if username is available and we're not editing the object.
    else if (this.id == 0 && registration.checkUsername() === "Yes") {
        this.usernameMesssage.css("color", "red");
        this.usernameMesssage.html("Sorry, the username is not available.");
        return false;
    }
    // if password is empty
    else if (!this.password) {
        this.passwordMessage.css("color", "red");
        this.passwordMessage.html("Sorry, the password can't be empty");
        return false;
    }
    //if password contains spaces
    else if (this.password.indexOf(" ")>=0) {
        this.passwordMessage.css("color", "red");
        this.passwordMessage.html("Sorry, the password can't contain spaces.");
        return false;
    }
    //if name is empty
    else if (!this.name) {
        clearErrors();
        this.nameMessage.css("color", "red");
        this.nameMessage.html("name can't be empty");
        return false;
    }
    //if name has digits
    else if (/\d/.test(this.name)) {
        clearErrors();
        this.nameMessage.css("color", "red");
        this.nameMessage.html("Name can't have any digit.");
        return false;
    }
    //if gender radio button is unselected
    else if (!$('input[name=gender]:checked').length > 0) {
        clearErrors();
        var genderMessage = $('#genderMessage');
        genderMessage.css("color", "red");
        genderMessage.html("Please select the gender");
        return false;
    }
    //if salary is unfilled.
    if (!this.salary) {
        clearErrors();
        this.salaryMessage.css("color", "red");
        this.salaryMessage.html("Please enter salary");
        return false;
    }
    //if salary isn't numeric
    else if (this.salary.match(/[^$,.\d]/)) {
        clearErrors();
        this.salaryMessage.css("color", "red");
        this.salaryMessage.html("salary can only contains numbers.");
        return false;
    }
    //if it's less than 5000
    else if (this.salary < 5000) {
        clearErrors();
        this.salaryMessage.css("color", "red");
        this.salaryMessage.html("Salary can't be less than 5000.");
        return false;
    }
    //if city is unfilled.
    else if (this.city === "Select your city") {
        clearErrors();
        this.cityMessage.css("color", "red");
        this.cityMessage.html("Please select the city");
        return false;
    }
    //when all the conditions pass, return true and hence perform action.
    return true;
}