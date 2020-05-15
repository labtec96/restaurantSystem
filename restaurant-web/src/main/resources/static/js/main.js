function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
    document.getElementById("openMenu").innerHTML = "";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft= "0";
    document.getElementById("openMenu").innerHTML = "☰";
}


$('document').ready(function () {

    $('.table .btn').on('click',function (event) {
        event.preventDefault();

        var href = $(this).attr('href')

        $.get(href, function (table,status) {
            $('#IdEdit').val(table.id)
            $('#numberEdit').val(table.number)
            $('#floorEdit').val(table.floor)
            $('#maxNumberOfPeopleEdit').val(table.maxNumberOfPeople)
            $('#vipEdit').val(table.vip)

        })

        $('#editModal').modal();
    });
});