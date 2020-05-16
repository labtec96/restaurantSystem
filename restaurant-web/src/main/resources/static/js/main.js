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

    $('.table .edit-table').on('click',function (event) {
        event.preventDefault();

        var href = $(this).attr('href')

        $.get(href, function (table,status) {
            $('#IdEdit').val(table.id)
            $('#numberEdit').val(table.number)
            $('#floorEdit').val(table.floor)
            $('#maxNumberOfPeopleEdit').val(table.maxNumberOfPeople)
        })

        $('#editModal').modal();
    });
});


$('document').ready(function () {

    $('.table .edit-meal').on('click',function (event) {
        event.preventDefault();

        var href = $(this).attr('href')

        $.get(href, function (meal,status) {
            $('#IdEdit').val(meal.id)
            $('#nameEdit').val(meal.name)
            $('#descriptionEdit').val(meal.description)
            $('#weightEdit').val(meal.weight)
            $('#priceEdit').val(meal.price)

        })

        $('#editModal').modal();
    });
});