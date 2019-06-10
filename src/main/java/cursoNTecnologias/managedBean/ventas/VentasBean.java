package cursoNTecnologias.managedBean.ventas;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import cursoNTecnologias.bd.domain.Productos;
import cursoNTecnologias.bd.domain.Ventas;
import cursoNTecnologias.service.ganancias.GananciasService;
import cursoNTecnologias.service.ventas.VentasService;

@ManagedBean
@ViewScoped
public class VentasBean implements Serializable{
	
	private static final long serialVersionUID = -7291372737200601998L;
	
	@Inject
	VentasService service;
	
	List<Ventas> ventasList = null;
	
	public void setVentasList(List<Ventas> ventasList) {
		this.ventasList = ventasList;
	}

	public List<Ventas> getVentasList() {
		if (ventasList == null)
			setVentasList(service.queryAllVentas());
		return ventasList;
	}

	public void onRowEdit(RowEditEvent event) {
		Ventas venta = ((Ventas) event.getObject());
		System.out.println("Datos ventas: " + venta);
		
		FacesMessage msg = new FacesMessage("Productos editado", Productos.getIdproducto().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edicion cancelada", ((Productos) event.getObject()).getIdproducto().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		System.out.println("verifica: " + newValue);
		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Productos modificado",
					"Antes: " + oldValue + ", Ahora: " + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
}
