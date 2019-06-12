package cursoNTecnologias.managedBean.direccion;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import cursoNTecnologias.bd.domain.Direccion;
import cursoNTecnologias.service.Direccion.DireccionService;

@ManagedBean
@ViewScoped
public class direccionBean {
	@Inject
	DireccionService direccionService;
	private List<Direccion> direccionList;
	private Direccion direccion;
	
	@PostConstruct
	public void init(){
		if(direccionList == null)
			direccionList = new ArrayList<Direccion>();
		if(direccion == null)
			direccion = new Direccion();
		//Se invoca el método del servicio para obtener las direcciones
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
	
	//metodo para registrar un nuevo cliente
	public void registrar(){
		System.out.println("Direccion");
		//invocar al servicio
		direccionService.insertarDireccion(getDireccion());
		//limpia los valores del objeto
		setDireccion(new Direccion());
		//Se actualiza los valores de la tabla
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
		FacesMessage mensaje = new FacesMessage("Edicion cancelada", ((Direccion) event.getObject()).getIddireccion().toString());
		FacesContext.getCurrentInstance().addMessage(null, mensaje);
	}

	public void onCellEdit(CellEditEvent event){
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		
		System.out.println("Verifica: " + newValue);
		if(newValue != null && !newValue.equals(oldValue)){
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Direccion modificado", "Antes: " + oldValue + ", Ahora: " + newValue); 
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		}	
	}

}
