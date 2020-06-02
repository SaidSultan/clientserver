$(function () {

    loadAllUsersOnPage();
});

$('#nav-UsersTable-tab').on('click', function (e) {
    e.preventDefault();
    loadAllUsersOnPage()
});
//УДАЛЕНИЕ ЮЗЕРА
$('#tbody').on('click', '.btn-danger', function () {
    if(confirm("Are you shure?")) {
        let intID = $(this).val();
        console.log(intID + "Id");
        console.log(typeof intID + "type of Id");

        $.ajax({
            url: "/rest/delete/",
            //contentType: 'application/json; charset=utf-8',
            type: "DELETE",
            data: {id: intID},
            success:  function () {
                console.log("ok");
                loadAllUsersOnPage();
            }
        });
        $('#home-tab').click()
    }
    //$('#home-tab').trigger('active')
});


// ДОБАВЛЕНИЕ ЮЗЕРА
$('#add_form').on("submit", function (e) {
    e.preventDefault();
    let uRoles = [];
   /* console.log( $('select[name="useRole"]').val())*/
    $('select[name="useRole"] option:selected').each(function () {
        console.log($(this).val());
        uRoles.push($(this).val());
    })

    let user = {
        name: $('#newUserName').val(),
        email: $('#newUserEmail').val(),
        login: $('#newUserLogin').val(),
        password: $('#newUserPass').val(),
        roles: uRoles
    }
    user = $.toJSON(user);
    console.log(user);
    $.ajax({
        url: "/rest/add",
        contentType: 'application/json; charset=utf-8',
        type: "POST",
        data: user,
        //dataType: "json",
        success: function () {
            console.log("ok");
            loadAllUsersOnPage();
            $('#home-tab').trigger('click');
        },
        error: function (data) {
            alert("error")
        },
        complete: function () {
            $('#add_form').each(function () {
                this.reset();  //очищается форма методом .reset()
            });
        }
    });
});


//в форме edit
$('#editUserForm').on('submit', function (e) {
    e.preventDefault()
    let uRoles = [];
    $('.form-check-input:checked').each(function () {
        uRoles.push($(this).val());
    });
    let st = null;
    $('.opt:selected').each(function () {
        st = ($(this).val());
    });

    let user = {
        id: $('#hidEditID').val(),
        name: $('#editName').val(),
        email: $('#editEmail').val(),
        login: $('#editLogin').val(),
        password: $('#editPass').val(),
        roles: uRoles
    }
    user = $.toJSON(user);
    $.ajax({
        url: "/rest/update",
        contentType: 'application/json; charset=utf-8',
        type: "PUT",
        data: user,
        dataType: "json",
        async: false,
        success: function (data) {
            loadAllUsersOnPage();
        },
        error: function (data) {
            alert("Ошибка! Редактирование не удалось.")
        },
        complete: function () {
            $('#editUserForm').each(function () {
                this.reset();  //очищается форма методом .reset()
            });
            $('#dismissModal').trigger('click');
            $('#home-tab').trigger('click');
        }
    });

});


function loadAllUsersOnPage() {
    $.get("/rest/all", function (allUsers) {
        console.log(allUsers + "all users");
        $('#tbody').empty();
        $.each(allUsers, function (i, user) {
            addUser(user)
        });
    });

}

function addUser(user) {
    let userRoles = user.roles;
    let r = ' ';
    $.each(userRoles, function (i, role) {
        r = r + role.name + "<br/>";
    });

    $('<tr id="' + user.id + '">').append(
        $('<td class="userID">').text(user.id),
        $('<td class="userNAME">').text(user.name),
        $('<td class="userEMAIL">').text(user.email),
        $('<td class="editLogin">').text(user.login),
        $('<td class="userROLES">').html(r),
        //КНОПКА EDIT
        $('<td>').html(
            "<button " +
            "class='btn btn-success' " +
            "id='editUserButton' " +
            "type='submit' " +
            "data-toggle='modal' " +
            "data-target= '#editUserModal' " +
            "onclick='openEditUserModal(" + user.id + ")' " +
            "value='edit" + user.id + "'>" +
            "Edit </button>"),
        //КНОПКА DEL
        $('<td>').html(
            "<button type='button' class='btn btn-danger' " +
            "value='" + user.id + "'>" +
            "Del</button></form>")

    ).appendTo('#tbody');
}

function openEditUserModal(id) {

    let uRl = '/rest/' + id;
    $.get(uRl, function (user) {

        console.log(user.roles)
        console.log(user.name)


        $('#exampleModalLabel').text(user.name);
        $('#hidEditID').attr('value', user.id);
        $('#editName').attr('value', user.name);
        $('#editEmail').attr('value', user.email);
        $('#editLogin').attr('value', user.login);

        for (let i = 0; i < user.roles.length; i++) {
            if (user.roles[i].name === "ADMIN") {
                $('#admEdit').prop('checked', true)
            } else if (user.roles[i].name === "USER") {
                $('#usEdit').prop('checked', true)
            }
        }
    });
}
