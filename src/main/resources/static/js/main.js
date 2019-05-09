$(document).ready(function () {

    $('.dropdown').click(function () {
        $(this).attr('tabindex', 1).focus();
        $(this).toggleClass('active');
        $(this).find('.dropdown-menu').slideToggle(300);
    });

    $('.dropdown').focusout(function () {
        $(this).removeClass('active');
        $(this).find('.dropdown-menu').slideUp(300);
    });

    $('.dropdown .dropdown-menu li').click(function () {
        $(this).parents('.dropdown').find('span').text($(this).text());
        $(this).parents('.dropdown').find('input').attr('value', $(this).attr('id'));
    });

    var table = $('#transactionTable').DataTable();
    var buttons = new $.fn.dataTable.Buttons(table, {
        buttons: [{
            extend: 'pdf',
            className: 'form-control form-control-sm page-custom resize'
        },
        {
            extend: 'csv',
            className: 'form-control form-control-sm page-custom resize'
        }]
    }).container().appendTo($('#table-actions'));



});



