function searchByCategoryId(categoryId) {
    window.location.href = '/searchByCat?categoryId='+categoryId;
}

function toggleSubCategory(category) {
    var subCategory = category.nextElementSibling;
    var categoryText = category.querySelector('.category-text');

    if (categoryText.classList.contains('highlight')) {
        categoryText.classList.remove('highlight');
    } else {
        categoryText.classList.add('highlight');
    }

    if (subCategory.style.display === 'none') {
        subCategory.style.display = 'block';
    } else {
        subCategory.style.display = 'none';
    }
}

document.querySelectorAll('.tab').forEach(function(span) {
    span.addEventListener('click', function() {
        document.querySelectorAll('.tab').forEach(function(s) {
            s.classList.remove('select');
        });
        this.classList.add('select');
    });
});
document.getElementById('tab1').addEventListener('click', function() {
    document.getElementById('tabContent').querySelectorAll('[id^=hot_]').forEach(element => element.style.display = 'none');
    document.getElementById('tabContent').querySelectorAll('[id^=recommended_]').forEach(element => element.style.display = 'none');
    document.getElementById('tabContent').querySelectorAll('[id^=latest_]').forEach(element => element.style.display = 'inline-block');
    document.getElementById('tabContent').querySelectorAll('[id^=latest_]').forEach(element => element.style.marginLeft = '5%');

});
document.getElementById('tab2').addEventListener('click', function() {
    document.getElementById('tabContent').querySelectorAll('[id^=hot_]').forEach(element => element.style.display = 'inline-block');
    document.getElementById('tabContent').querySelectorAll('[id^=hot_]').forEach(element => element.style.marginLeft = '5%');
    document.getElementById('tabContent').querySelectorAll('[id^=recommended_]').forEach(element => element.style.display = 'none');
    document.getElementById('tabContent').querySelectorAll('[id^=latest_]').forEach(element => element.style.display = 'none');
});
document.getElementById('tab3').addEventListener('click', function() {
    document.getElementById('tabContent').querySelectorAll('[id^=recommended_]').forEach(element => element.style.display = 'inline-block');
    document.getElementById('tabContent').querySelectorAll('[id^=recommended_]').forEach(element => element.style.marginLeft = '5%');
    document.getElementById('tabContent').querySelectorAll('[id^=hot_]').forEach(element => element.style.display = 'none');
    document.getElementById('tabContent').querySelectorAll('[id^=latest_]').forEach(element => element.style.display = 'none');
});