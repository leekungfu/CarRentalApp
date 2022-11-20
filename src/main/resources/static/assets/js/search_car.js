$(document).ready(function () {
     $('.js-btn-view-detail').each(function (){
        $(this).click(function (){
            const modalID = '#' + $(this).val();
            $(modalID).modal('show');
        })
     })
});