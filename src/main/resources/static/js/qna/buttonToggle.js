function invalidateAll() {
    const btn = $('.toggle-btn');
    btn.removeClass('btn-primary');
    btn.addClass('btn-secondary');
}

(function () {
    $('.toggle-btn').click(function () {
        console.log("toggle Btn Clicked");
        invalidateAll();

        if ($(this).hasClass('btn-secondary')) {
            $(this).addClass('btn-primary');
            $(this).removeClass('btn-secondary');
        }
    });
})()
