package sv.edu.udb.www.lastone.Modelo;

public class Departamento {
    // Atributos de la clase que representan los datos del departamento
    private int id; // ID del departamento, generalmente autoincremental en la base de datos
    private String nombre; // Nombre del departamento
    private String descripcion; // Descripción del departamento

    // Constructor por defecto, sin parámetros
    public Departamento() {}

    // Constructor que recibe los parámetros para inicializar el departamento
    // Se utiliza para crear un departamento con un ID, nombre y descripción especificados
    public Departamento(int id, String nombre, String descripcion) {
        this.id = id; // Asignamos el valor del ID
        this.nombre = nombre; // Asignamos el valor del nombre
        this.descripcion = descripcion; // Asignamos el valor de la descripción
    }

    // Métodos getter y setter para cada atributo, permiten acceder y modificar los valores de los atributos

    // Metodo getter para obtener el valor de 'id'
    public int getId() {
        return id;
    }

    // Metodo setter para modificar el valor de 'id'
    public void setId(int id) {
        this.id = id;
    }

    // Metodo getter para obtener el valor de 'nombre'
    public String getNombre() {
        return nombre;
    }

    // Metodo setter para modificar el valor de 'nombre'
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Metodo getter para obtener el valor de 'descripcion'
    public String getDescripcion() {
        return descripcion;
    }

    // Metodo setter para modificar el valor de 'descripcion'
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
