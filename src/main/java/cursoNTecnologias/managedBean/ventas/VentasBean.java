package cursoNTecnologias.managedBean.ventas;

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

import cursoNTecnologias.bd.domain.Cliente;
import cursoNTecnologias.bd.domain.Ventas;
import cursoNTecnologias.service.ventas.VentasService;

@Named
@ViewScoped
public class VentasBean implements Serializable {
	private static final long serialVersionUID = 6705556644665739772L;
	
	@Inject
	VentasService service;
	
	List<Ventas> ventasList = null;
	
	private Cliente cliente;
	private Integer idventa;
    private Integer clienteid;
    private Double totalventa;
    private Date fecha;

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Integer getIdventa() {
		return idventa;
	}
	public void setIdventa(Integer idventa) {
		this.idventa = idventa;
	}
	public Integer getClienteid() {
		return clienteid;
	}
	public void setClienteid(Integer clienteid) {
		this.clienteid = clienteid;
	}
	public Double getTotalventa() {
		return totalventa;
	}
	public void setTotalventa(Double totalventa) {
		this.totalventa = totalventa;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public void setVentasList(List<Ventas> ventasList) {
		this.ventasList = ventasList;
	}
	public List<Ventas> getVentasList() {
		if (ventasList == null)
			setVentasList(service.queryAllVentas());
		return ventasList;
	}
	public void onRowEdit(RowEditEvent event) {
		System.out.println("On row edit");
		Ventas venta = ((Ventas) event.getObject());
		System.out.println("Datos ventas: \n" + venta);
		service.updateVenta(venta);
		FacesMessage msg = new FacesMessage("Venta editada", venta.getIdventa().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		System.out.println("On row cancel");
		FacesMessage msg = new FacesMessage("Edicion cancelada", ((Ventas) event.getObject()).getIdventa().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCellEdit(CellEditEvent event) {
		System.out.println("On cell edit");
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		System.out.println("verifica: " + newValue);
		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Venta modificada",
					"Antes: " + oldValue + ", Ahora: " + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
}
