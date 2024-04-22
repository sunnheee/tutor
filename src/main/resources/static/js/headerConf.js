function searchByKeyword() {
    var keyword = $('#query').val();
    if(keyword && keyword!='') {
        window.location.href = '/searchByKeyword?keyword=' + keyword;
    }
}

document.getElementById('logo').addEventListener('click', function() {
    window.location.href = '/index';
});
