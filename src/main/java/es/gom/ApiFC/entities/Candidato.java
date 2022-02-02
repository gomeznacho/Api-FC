package es.gom.ApiFC.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="candidatos")
public class Candidato implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name= "nombre_completo", nullable=false, length=45)
    private String nombreCompleto;

    @Column(nullable=false, length=20)
    private String ciudad;

    @Column(nullable=false, length=20)
    private String pais;

    @Column(nullable=false, unique = true)
    private String telefono;

    @Column(nullable=false, length=45, unique = true)
    private String email;

    private boolean movilidad;

    @Column
    @Enumerated(EnumType.STRING)
    private Presencialidad presencialidad;

    @JsonIgnoreProperties(value={"candidatos"})
    @ManyToOne
    @JoinTable(name="candidatos_usuarios",
            joinColumns= @JoinColumn(name="candidato_id", foreignKey=@ForeignKey(name="fk2_candidato_id")),
            inverseJoinColumns= @JoinColumn(name="usuario_id", foreignKey=@ForeignKey(name="fk2_usuario_id")))
    private Usuario usuario;

    @JsonIgnoreProperties(value={"candidatos"})
    @ManyToMany
    @JoinTable(name="candidatos_etiquetas",
            joinColumns= @JoinColumn(name="candidato_id", foreignKey=@ForeignKey(name="fk_candidato_id")),
            inverseJoinColumns= @JoinColumn(name="etiqueta_id", foreignKey=@ForeignKey(name="fk_etiqueta_id")))
    private List<Etiqueta> etiquetas = new ArrayList<>();



    public Candidato() {
    }

    public Candidato(Long id, String nombreCompleto, String ciudad, String pais, String telefono, String email, boolean movilidad, Presencialidad presencialidad) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.ciudad = ciudad;
        this.pais = pais;
        this.telefono = telefono;
        this.email = email;
        this.movilidad = movilidad;
        this.presencialidad = presencialidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isMovilidad() {
        return movilidad;
    }

    public void setMovilidad(boolean movilidad) {
        this.movilidad = movilidad;
    }

    public Presencialidad getPresencialidad() {
        return presencialidad;
    }

    public void setPresencialidad(Presencialidad presencialidad) {
        this.presencialidad = presencialidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(ArrayList<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    @Override
    public String toString() {
        return "Candidato{" +
                "id=" + id +
                ", nombre='" + nombreCompleto + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", pais='" + pais + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", movilidad=" + movilidad +
                ", presencialidad=" + presencialidad +
                //", userId=" + usuario +
                //", etiquetas=" + etiquetas +
                '}';
    }
}


