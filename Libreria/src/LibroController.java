@RestController
@RequestMapping("/libros")
public class LibroController {
    @Autowired
    private LibroRepository libroRepository;

    @GetMapping
    public List<Libro> getAllLibros() {
        return libroRepository.findAll();
    }

    @GetMapping("/{id}")
    public Libro getLibroById(@PathVariable Long id) {
        return libroRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Libro createLibro(@RequestBody Libro libro) {
        return libroRepository.save(libro);
    }

    @PutMapping("/{id}")
    public Libro updateLibro(@PathVariable Long id, @RequestBody Libro libroDetails) {
        Libro libro = libroRepository.findById(id).orElse(null);
        if (libro != null) {
            libro.setTitulo(libroDetails.getTitulo());
            libro.setAutor(libroDetails.getAutor());
            libro.setEditorial(libroDetails.getEditorial());
            libro.setAnioPublicacion(libroDetails.getAnioPublicacion());
            libro.setGenero(libroDetails.getGenero());
            return libroRepository.save(libro);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteLibro(@PathVariable Long id) {
        libroRepository.deleteById(id);
    }

    @GetMapping("/search")
    public List<Libro> searchLibros(@RequestParam(required = false) String titulo,
                                    @RequestParam(required = false) String autor,
                                    @RequestParam(required = false) String editorial) {
        if (titulo != null) {
            return libroRepository.findByTituloContaining(titulo);
        } else if (autor != null) {
            return libroRepository.findByAutorContaining(autor);
        } else if (editorial != null) {
            return libroRepository.findByEditorialContaining(editorial);
        }
        return new ArrayList<>();
    }
}
