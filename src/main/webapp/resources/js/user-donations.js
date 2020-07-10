/**
 * Show user donations
 */
$('li.showUserContent').click(function () {
    let id = $(this).attr('rel');
    let selectedContent = $('#'+id).slideToggle('slow');
    $('h1.hideUserNameH1').replaceWith(selectedContent);
})