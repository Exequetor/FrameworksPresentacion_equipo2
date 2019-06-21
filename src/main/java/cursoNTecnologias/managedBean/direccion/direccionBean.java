package cursoNTecnologias.managedBean.direccion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import cursoNTecnologias.bd.domain.Cliente;
import cursoNTecnologias.bd.domain.Direccion;
import cursoNTecnologias.bd.domain.Productos;
import cursoNTecnologias.service.Direccion.DireccionService;

@Named
@ViewScoped
public class direccionBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3730798503563481908L;
	@Inject
	DireccionService direccionService;
	private List<Direccion> direccionList;
	private Direccion direccion;
	private String calle;
	private Integer numero;
	private String colonia;
	private String estado;
	private String ciudad;
	private String pais;
	private Integer codigopostal;
	

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public Integer getCodigopostal() {
		return codigopostal;
	}

	public void setCodigopostal(Integer codigopostal) {
		this.codigopostal = codigopostal;
	}

	@PostConstruct
	public void init() {
		if (direccionList == null)
			direccionList = new ArrayList<Direccion>();
		if (direccion == null)
			direccion = new Direccion();
		// Se invoca el método del servicio para obtener las direcciones
		setDireccionList(direccionService.listarDireccion());
	}

	public List<Direccion> getDireccionList() {
		if (direccionList == null)
			setDireccionList(direccionService.listarDireccion());
		return direccionList;
	}

	public void setDireccionList(List<Direccion> direccionList) {
		this.direccionList = direccionList;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	// metodo para registrar un nuevo cliente
	public void registrar() {
		System.out.println("Direccion");
		// invocar al servicio
		direccionService.insertarDireccion(getDireccion());
		// limpia los valores del objeto
		setDireccion(new Direccion());
		// Se actualiza los valores de la tabla
		setDireccionList(direccionService.listarDireccion());
		getDireccionList();
	}
	
	//registrar una nueva direccion
	
	public void agregar() {
		Direccion dir=new Direccion();
		dir.setCalle(calle);
		dir.setCiudad(ciudad);
		dir.setCodigopostal(codigopostal);
		dir.setColonia(colonia);
		dir.setEstado(estado);
		dir.setNumero(numero);
		dir.setPais(pais);
		direccionService.insertarDireccion(dir);
		setDireccionList(direccionService.listarDireccion());

	}

	public void eliminar(Integer id) {
		direccionService.eliminarDireccion(id);
		setDireccion(new Direccion());
		setDireccionList(direccionService.listarDireccion());
		getDireccionList();
	}

	public void onRowEdit(RowEditEvent event) {
		Direccion direccion = ((Direccion) event.getObject());
		System.out.println("Datos de la dirección: " + direccion.getIddireccion());
		direccionService.actualizarDireccion(direccion);

		FacesMessage mensaje = new FacesMessage("Direccion editado", direccion.getIddireccion().toString());
		FacesContext.getCurrentInstance().addMessage(null, mensaje);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage mensaje = new FacesMessage("Edicion cancelada",
				((Direccion) event.getObject()).getIddireccion().toString());
		FacesContext.getCurrentInstance().addMessage(null, mensaje);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		System.out.println("Verifica: " + newValue);
		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Direccion modificado",
					"Antes: " + oldValue + ", Ahora: " + newValue);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		}
	}

}
