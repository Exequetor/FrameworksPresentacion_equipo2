package cursoNTecnologias.managedBean.marcas;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import cursoNTecnologias.bd.domain.Marcas;
import cursoNTecnologias.service.marcas.MarcasService;
import cursoNTecnologias.service.marcas.MarcasServiceImpl;

@Named
@ViewScoped
public class MarcasBean {
	@Inject
	MarcasServiceImpl marcasService;
	private List<Marcas> MarcasList;
	private Marcas marca;
	private String nombre;

	@PostConstruct
	public void init() {
		if (MarcasList == null)
			MarcasList = new ArrayList<Marcas>();
		if (marca == null)
			marca = new Marcas();
		// se invoca el método del servicio para obtener las marcas
		setMarcasList(marcasService.obtenerTodasMarca());
	}

	public Marcas getMarca() {
		return marca;
	}

	public void setMarca(Marcas marca) {
		this.marca = marca;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return this.nombre;
	}

	public List<Marcas> getMarcasList() {
		if (MarcasList == null)
			setMarcasList(marcasService.listarMarcas());
		return MarcasList;
	}

	public void setMarcasList(List<Marcas> MarcasList) {
		this.MarcasList = MarcasList;
	}

	public void onRowEdit(RowEditEvent event) {
		Marcas Marcas = ((Marcas) event.getObject());
		System.out.println("datos Marcas: " + Marcas.getIdmarca());
		marcasService.updateMarca(Marcas);
		FacesMessage msg = new FacesMessage("Marcas editado", Marcas.getIdmarca().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edicion cancelada", ((Marcas) event.getObject()).getIdmarca().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		System.out.println("verifica: " + newValue);
		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Marcas modificado",
					"Antes: " + oldValue + ", Ahora: " + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void deleteBtn(Integer id) {
		System.out.println("Marca con id " + id + " eliminada");
		marcasService.deleteMarca(id);
		FacesMessage msg = new FacesMessage("Marcas eliminada" + id);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		setMarcasList(marcasService.listarMarcas());
	}

	public void agregar() {
		System.out.println("Marca " + this.nombre + " agregada");
		Marcas marca = new Marcas();
		marca.setIdmarca(1);
		marca.setNombremarca(this.nombre);
		marcasService.insertMarca(marca);
	}


}
