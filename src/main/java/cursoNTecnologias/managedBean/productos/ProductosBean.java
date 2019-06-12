package cursoNTecnologias.managedBean.productos;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import cursoNTecnologias.bd.domain.Marcas;
import cursoNTecnologias.bd.domain.Productos;
import cursoNTecnologias.service.productos.ProductosService;
import cursoNTecnologias.service.productos.ProductosServiceImpl;


@Named
public class ProductosBean {
	@Inject
	ProductosServiceImpl productosService;
	private List<Productos> ProductosList;
	
	private String nombre;
	private double precio;
	private double precioVta;
	private int cantidad;
	
	private String marca;
	
	
	
	public void setMarca(String marca) {
		this.marca=marca;
	}
	public String getMarca() {return this.marca;}
	
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	public String getNombre() {return this.nombre;}
	public void setPrecio(double precio) {
		this.precio=precio;
	}
	public double getPrecio() {return this.precio;}
	public void setPrecioVta(double precioVta) {
		this.precioVta=precioVta;
	}
	public double getPrecioVta() {return this.precioVta;}
	public void setCantidad(int cantidad) {
		this.cantidad=cantidad;
	}
	public int getCantidad() {return this.cantidad;}

	public List<Productos> getProductosList() {
		if (ProductosList == null)
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
	
	public void registrar() {
		System.out.println("Entre al registro "+ marca.getClass());
		/*System.out.print(marca.getNombremarca() +" --> "+marca.getIdmarca());
		Productos p=new Productos();
		p.setNombre(nombre);
		p.setPrecio(precio);
		p.setPreciovta(precioVta);
		p.setMarcaid(marca.getIdmarca());
		productosService.agregarProducto(p);*/
	}
}
