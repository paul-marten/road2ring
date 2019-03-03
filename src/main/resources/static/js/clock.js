$(window).load(function() {
    var monthNames = ["Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul", "Agu", "Sep", "Okt", "Nov", "Des"];
    var dayNames = ["Minggu", "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"];
    var newDate = new Date();
    newDate.setDate(newDate.getDate());
    setInterval(function() {
        $('.date').html(dayNames[newDate.getDay()] + ", " + newDate.getDate() + " " + monthNames[newDate.getMonth()] + " " + newDate.getFullYear() + " - ");
    }, 1000);

    setInterval(function() {
        var seconds = new Date().getSeconds();
        $(".sec").html((seconds < 10 ? "0" : "") + seconds);
    }, 1000);

    setInterval(function() {
        var minutes = new Date().getMinutes();
        $(".min").html((minutes < 10 ? "0" : "") + minutes + " : ");
    }, 1000);

    setInterval(function() {
        var hours = new Date().getHours();
        $(".hours").html((hours < 10 ? "0" : "") + hours + " : ");
    }, 1000);
});
