var elem = document.querySelector('.grid');
var msnry = new Masonry( elem, {
  // Select items
  itemSelector: '.grid-item',
  // Set width
  columnWidth: '.grid-item',
  fitWidth: true,
});