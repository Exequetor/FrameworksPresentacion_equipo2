package cursoNTecnologias.managedBean.productos;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import cursoNTecnologias.bd.domain.Productos;
import cursoNTecnologias.service.productos.ProductosService;


@ManagedBean
public class ProductosBean {
	@Inject
	ProductosService productosService;
	private List<Productos> ProductosList;

	public List<Productos> getProductosList() {
		if (ProductosList != null)
			setProductosList(productosService.listarProductos());
		return ProductosList;
	}

	public void setProductosList(List<Productos> ProductosList) {
		this.ProductosList = ProductosList;
	}

	public void onRowEdit(RowEditEvent event) {
		Productos Productos = ((Productos) event.getObject());
		System.out.println("datos Productos: " + Productos.getIdproducto());
		productosService.updateProducto(Productos);
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
