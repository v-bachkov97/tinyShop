let tShirtsBtn = document.querySelector('.tShirtBtn');
let suitsBtn = document.querySelector('.suitsBtn');
let shortsBtn = document.querySelector('.shortsBtn');
let accessoriesBtn = document.querySelector('.accessoriesBtn');

let urlShirts = 'http://localhost:8080/collections/men/shirts';
let urlSuits = 'http://localhost:8080/collections/men/suits';
let urlShorts = 'http://localhost:8080/collections/men/shorts';
let urlAccessories = 'http://localhost:8080/collections/men/accessories';

tShirtsBtn.addEventListener('click', loadTShirts);
suitsBtn.addEventListener('click', loadSuits);
shortsBtn.addEventListener('click', loadShorts);
accessoriesBtn.addEventListener('click', loadAccessories);


function loadTShirts() {
    window.location.href = urlShirts;

}function loadSuits() {
    window.location.href = urlSuits;

}function loadShorts() {
    window.location.href = urlShorts;

}function loadAccessories() {
    window.location.href = urlAccessories;
}
