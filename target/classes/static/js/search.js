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