let tShirtsBtn = document.querySelector('.tShirtBtn');
let hoodiesBtn = document.querySelector('.hoodiesBtn');
let shortsBtn = document.querySelector('.shortsBtn');
let accessoriesBtn = document.querySelector('.accessoriesBtn');

let urlShirts = 'http://localhost:8080/collections/kids/shirts';
let urlSuits = 'http://localhost:8080/collections/kids/hoodies';
let urlShorts = 'http://localhost:8080/collections/kids/shorts';
let urlAccessories = 'http://localhost:8080/collections/kids/accessories';

tShirtsBtn.addEventListener('click', loadTShirts);
hoodiesBtn.addEventListener('click', loadHoodies);
shortsBtn.addEventListener('click', loadShorts);
accessoriesBtn.addEventListener('click', loadAccessories);


function loadTShirts() {
    window.location.href = urlShirts;

}function loadHoodies() {
    window.location.href = urlSuits;

}function loadShorts() {
    window.location.href = urlShorts;

}function loadAccessories() {
    window.location.href = urlAccessories;
}
