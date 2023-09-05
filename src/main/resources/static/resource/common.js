$('select[data-value]').each(function (index, item){

    const items = $(item)
    const defaultValue = items.attr('data-value').trim();

    if (defaultValue.length > 0){
        items.val(defaultValue);
    }
})

$('.modal-exam').click(function (){
    $('.layer').show();
    $('.layer-bg').show();
})

$('#close-btn').click(function (){
    $('.layer').hide();
    $('.layer-bg').hide();
})

$('.layer-bg').click(function (){
    $('.layer').hide();
    $('.layer-bg').hide();
})

$('#close-x-btn').click(function (){
    $('.layer').hide();
    $('.layer-bg').hide();
})