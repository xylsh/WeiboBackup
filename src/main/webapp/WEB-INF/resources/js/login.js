function bind_form_validate() {
    $("#loginForm").validate({
//            errorClass: "tip",
        rules: {
            username: { required: true },
            password: { required: true }
        },
        messages: {
            username: {required: "请填入用户名！"},
            password: {required: "请填入密码！"}
        },
        errorPlacement: function (error, element) {
            error.appendTo(".tip");
        }
    });
}

$(function () {
    bind_form_validate();
});