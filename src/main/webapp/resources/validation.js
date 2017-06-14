function Registration() {
    var _id = null;
    var _username = null;
    var _password = null;
    var _name = null;
    var _salary = null;
    var _city = null;
    var _usernameMessage = null;
    var _passwordMessage = null;
    var _nameMessage = null;
    var _salaryMessage = null;
    var _cityMessage = null;

    initializeFields = function () {
        _id = $('#id');
        _username = $('#userName');
        _password = $('#password');
        _name = $('#name');
        _salary = $('#salary');
        _city = jQuery("#city").find("option:selected");
        _usernameMessage = $('#userNameMessage');
        _passwordMessage = $('#passwordMessage');
        _nameMessage = $('#nameMessage');
        _salaryMessage = $('#salaryMessage');
        _cityMessage = $('#cityMessage');
    };

    this.init = function () {
        initializeFields();
        _username.blur(function () {
            var name = _username.val();
            var userMessage = $('#userNameMessage');
            var id = $('#id').val();

            //checking for spaces.
            if (name.indexOf(" ") >= 0) {
                userMessage.css("color", "red");
                userMessage.html("Sorry, the username can't contain spaces.");
                return;
            }

            var resp = registration.checkUsername();
            //making sure with id that these messages are not shown at the time of editing. ie. id is not 0.
            if (resp === "Yes" && id == 0) {
                userMessage.css("color", "red");
                userMessage.html("Sorry, the username is not available.");
            }
            else if (resp === "No" && id == 0) {
                userMessage.css("color", "green");
                userMessage.html("Congrats, Your username is available.");
            }
        });
    };

    this.checkUsername = function () {
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
        if (password.length == 0) {
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

    this.checkSpaces = function (string) {
        return string.indexOf(" ") >= 0;
    };

    this.clearError = function () {
        $('.errmsg').html("");
    };

    this.checkVal = function () {
        initializeFields();
        var status = true;
        //if username is empty
        if (!_username.val()) {
            _usernameMessage.css("color", "red");
            _usernameMessage.html("Sorry, the username can't be empty.");
            status = false;
        }
        // if username contains spaces
        else if (registration.checkSpaces(_username.val())) {
            _usernameMessage.css("color", "red");
            _usernameMessage.html("Sorry, the username can't contain spaces");
            status = false;
        }

        // if username is available and we're not editing the object.
        else if (_id.val() == 0 && registration.checkUsername() === "Yes") {
            _usernameMessage.css("color", "red");
            _usernameMessage.html("Sorry, the username is not available.");
            status = false;
        }
        // if password is empty
        else if (!_password.val()) {
            _passwordMessage.css("color", "red");
            _passwordMessage.html("Sorry, the password can't be empty");
            status = false;
        }
        //if password contains spaces
        else if (registration.checkSpaces(_password.val())) {
            _passwordMessage.css("color", "red");
            _passwordMessage.html("Sorry, the password can't contain spaces.");
            status = false;
        }
        else if (!_name.val()) {
            registration.clearError();
            _nameMessage.css("color", "red");
            _nameMessage.html("name can't be empty");
            status = false;
        }
        //if name has digits
        else if (/\d/.test(_name.val())) {
            registration.clearError();
            _nameMessage.css("color", "red");
            _nameMessage.html("Name can't have any digit.");
            status = false;
        }
        //if gender radio button is unselected
        else if (!$('input[name=gender]:checked').length > 0) {
            registration.clearError();
            var genderMessage = $('#genderMessage');
            genderMessage.css("color", "red");
            genderMessage.html("Please select the gender");
            status = false;
        }
        //if salary is unfilled.
        else if (!_salary.val()) {
            registration.clearError();
            _salaryMessage.css("color", "red");
            _salaryMessage.html("Please enter salary");
            status = false;
        }
        //if salary isn't numeric
        else if (_salary.val().match(/[^$,.\d]/)) {
            registration.clearError();
            _salaryMessage.css("color", "red");
            _salaryMessage.html("salary can only contains numbers.");
            status = false;
        }
        //if it's less than 5000
        else if (_salary.val() < 5000) {
            registration.clearError();
            _salaryMessage.css("color", "red");
            _salaryMessage.html("Salary can't be less than 5000.");
            status = false;
        }
        //if city is unfilled.
        else if (_city.text() === "Select your city") {
            registration.clearError();
            _cityMessage.css("color", "red");
            _cityMessage.html("Please select the city");
            status = false;
        }
        return status;
    }
}

var registration = new Registration();

$(document).ready(function () {
    registration.init();
    $('#password').keyup(function () {
        $('#passwordMessage').html(registration.checkStrength())
    });
});

function returnVal() {
    return registration.checkVal();
}
