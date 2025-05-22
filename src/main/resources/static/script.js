const API_BASE = "http://localhost:8080/api/books";

function fetchBooks() {
  fetch(API_BASE)
    .then(res => res.json())
    .then(data => {
      const list = document.getElementById("book-list");
      list.innerHTML = "";
      data.forEach(book => {
        const div = document.createElement("div");
        div.innerHTML = `<strong>${book.title}</strong> by ${book.author} 
        <button onclick="deleteBook(${book.id})">Delete</button>`;
        list.appendChild(div);
      });
    });
}

function addBook() {
  const title = document.getElementById("title").value;
  const author = document.getElementById("author").value;

  fetch(API_BASE, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({ title, author })
  })
    .then(() => {
      document.getElementById("title").value = "";
      document.getElementById("author").value = "";
      fetchBooks();
    });
}

function deleteBook(id) {
  fetch(`${API_BASE}/${id}`, {
    method: "DELETE"
  }).then(() => fetchBooks());
}

// Load books on page load
window.onload = fetchBooks;

function fetchReviews(bookId) {
  fetch(`/api/reviews/book/${bookId}`)
    .then(response => response.json())
    .then(reviews => {
      const reviewList = document.getElementById(`reviews-${bookId}`);
      reviewList.innerHTML = '';
      reviews.forEach(review => {
        const li = document.createElement('li');
        li.textContent = `${review.comment} (Rating: ${review.rating})`;
        reviewList.appendChild(li);
      });
    });
}

function addReview(event, bookId) {
  event.preventDefault();

  const comment = document.getElementById(`comment-${bookId}`).value;
  const rating = document.getElementById(`rating-${bookId}`).value;

  const token = localStorage.getItem('jwt'); // Make sure token is stored after login

  fetch(`/api/reviews/book/${bookId}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + token
    },
    body: JSON.stringify({
      comment: comment,
      rating: rating
    })
  })
  .then(response => {
    if (response.ok) {
      fetchReviews(bookId); // Refresh reviews
      document.getElementById(`comment-${bookId}`).value = '';
      document.getElementById(`rating-${bookId}`).value = '';
    } else {
      alert("Failed to add review. Make sure you're logged in.");
    }
  });
}
