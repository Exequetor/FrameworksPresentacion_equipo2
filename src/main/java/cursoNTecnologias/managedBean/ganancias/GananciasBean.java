package cursoNTecnologias.managedBean.ganancias;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import cursoNTecnologias.bd.domain.Ganancias;
import cursoNTecnologias.service.ganancias.GananciasService;

@Named
@ViewScoped
public class GananciasBean implements Serializable{
	
	private static final long serialVersionUID = -5747820911836671695L;
	
	@Inject
	GananciasService service;
	
	private Integer idganancia;
    private Integer ventaid;
    private Double totalganancia;
    private Date fecha;
    
    List<Ganancias> gananciasList = null;
    
    public void setGananciasList(List<Ganancias> gananciasList) {
		this.gananciasList = gananciasList;
	}
	public List<Ganancias> getGananciasList() {
		if (gananciasList == null)
			setGananciasList(service.queryAllGanancias());
		return gananciasList;
	}
	
	public GananciasService getService() {
		return service;
	}
	public void setService(GananciasService service) {
		this.service = service;
	}
	public Integer getIdganancia() {
		return idganancia;
	}
	public void setIdganancia(Integer idganancia) {
		this.idganancia = idganancia;
	}
	public Integer getVentaid() {
		return ventaid;
	}
	public void setVentaid(Integer ventaid) {
		this.ventaid = ventaid;
	}
	public Double getTotalganancia() {
		return totalganancia;
	}
	public void setTotalganancia(Double totalganancia) {
		this.totalganancia = totalganancia;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public void onRowEdit(RowEditEvent event) {
		System.out.println("On row edit-----------------------------------------------------");
		Ganancias ganancia = ((Ganancias) event.getObject());
		System.out.println(ganancia.getVenta());
		System.out.println("Datos Ganancias: \n" + ganancia);
		service.updateGanancia(ganancia);
		setGananciasList(service.queryAllGanancias());
		FacesMessage msg = new FacesMessage("ganancia editada", ganancia.getIdganancia().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		System.out.println("On row cancel");
		FacesMessage msg = new FacesMessage("Edicion cancelada", ((Ganancias) event.getObject()).getIdganancia().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCellEdit(CellEditEvent event) {
		System.out.println("On cell edit");
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		System.out.println("verifica: " + newValue);
		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "ganancia modificada",
					"Antes: " + oldValue + ", Ahora: " + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
}
