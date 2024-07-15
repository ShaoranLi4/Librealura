function buscarLibros() {
    const titulo = document.getElementById('titulo').value;
    const autor = document.getElementById('autor').value;
    const editorial = document.getElementById('editorial').value;

    let url = 'http://localhost:8080/libros/search?';
    if (titulo) {
        url += `titulo=${titulo}&`;
    }
    if (autor) {
        url += `autor=${autor}&`;
    }
    if (editorial) {
        url += `editorial=${editorial}&`;
    }

    fetch(url)
        .then(response => response.json())
        .then(data => {
            const resultadosDiv = document.getElementById('resultados');
            resultadosDiv.innerHTML = '';
            data.forEach(libro => {
                const libroDiv = document.createElement('div');
                libroDiv.innerHTML = `<h3>${libro.titulo}</h3><p>Autor: ${libro.autor}</p><p>Editorial: ${libro.editorial}</p><p>Año: ${libro.anioPublicacion}</p><p>Género: ${libro.genero}</p>`;
                resultadosDiv.appendChild(libroDiv);
            });
        })
        .catch(error => console.error('Error:', error));
}
